package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public DatePicker pickEndDate;
    @FXML
    public DatePicker pickStartDate;
    @FXML
    public TextField stayPeriod = new TextField();
    @FXML
    public Button countButton;
    @FXML
    public Button addDataToTable;
    @FXML
    public DatePicker enterDate;
    @FXML
    public DatePicker exitDate;
    @FXML
    public TextArea textArea;
    @FXML
    public Button cleanUp;


    private StringBuilder result = new StringBuilder();


    private DateObject dateObject = new DateObject();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private Utils utils = new Utils();

    public Controller() {
    }

    public void buttonAction(ActionEvent actionEvent) {

        Button button = (Button) actionEvent.getSource();


        if (button.getText().equals("+")) {
            String enterDateValidationResult = utils.dateValidation(enterDate.getEditor().getText());
            String exitDateValidationResult = utils.dateValidation(exitDate.getEditor().getText());

            if (!enterDateValidationResult.equals("x") && !exitDateValidationResult.equals("x")) {

                enterDate.setValue(enterDate.getConverter().fromString(enterDateValidationResult));
                exitDate.setValue(exitDate.getConverter().fromString(exitDateValidationResult));

                dateObject.addEnterExitDate(enterDate.getValue(), exitDate.getValue());

                updateTextArea();
            } else {
                System.out.println("enter date or exit date is not valid");
            }


        } else if (button.getText().equals("—")) {
            String enterDateValidationResult = utils.dateValidation(enterDate.getEditor().getText());
            String exitDateValidationResult = utils.dateValidation(exitDate.getEditor().getText());

            if (!enterDateValidationResult.equals("x") && !exitDateValidationResult.equals("x")) {
                enterDate.setValue(enterDate.getConverter().fromString(enterDate.getEditor().getText()));
                exitDate.setValue(exitDate.getConverter().fromString(exitDate.getEditor().getText()));

                LocalDate enterDateValue = enterDate.getValue();
                LocalDate exitDateValue = exitDate.getValue();

                for (int i = 0; i < dateObject.getEnterExitDate().size(); i++) {
                    LocalDate[] dates = dateObject.getEnterExitDate().get(i);
                    LocalDate enterDate = dates[0];
                    LocalDate exitDate = dates[1];

                    if (enterDate.isEqual(enterDateValue)) {

                        if (exitDate.isEqual(exitDateValue)) {
                            dateObject.getEnterExitDate().remove(i);
                            updateTextArea();
                            break;
                        }
                    }

                }
            } else {
                System.out.println("enter date or exit date is not valid");
            }

        } else if (button.getText().equals("Очистити все")) {
            pickEndDate.getEditor().setText("");
            pickStartDate.getEditor().setText("");
            textArea.setText("");
            stayPeriod.setText("");
            enterDate.getEditor().setText("");
            exitDate.getEditor().setText("");
            dateObject = new DateObject();
        } else {
            String endDateValidationResult = utils.dateValidation(pickEndDate.getEditor().getText());
            String startDateValidationResult = utils.dateValidation(pickStartDate.getEditor().getText());


            if (!endDateValidationResult.equals("x") && !startDateValidationResult.equals("x")) {
                pickEndDate.setValue(pickEndDate.getConverter().fromString(endDateValidationResult));
                pickStartDate.setValue(pickStartDate.getConverter().fromString(startDateValidationResult));

                dateObject.setStartDate(pickStartDate.getValue());
                dateObject.setEndDate(pickEndDate.getValue());

                if (!utils.numberValidation(stayPeriod.getText()).equals("x")) {
                    dateObject.setStayPeriod(Integer.valueOf(stayPeriod.getText()));

                    if (dateObject.isHaveNotNullFiled(dateObject)) {
                        ReportGenerator reportGenerator = new ReportGenerator(dateObject);
                        reportGenerator.saveAndOpenResultFile();
                    }

                } else {
                    System.out.println("stay period filed is ont valid");
                }

            } else {
                System.out.println("start date or end date is not valid");
            }
        }
    }

    private void updateTextArea() {
        for (LocalDate[] dates : dateObject.getEnterExitDate()) {
            String date1 = dates[0].format(formatter);
            String date2 = dates[1].format(formatter);
            String daysBetweenDates = utils.daysBetweenDate(dates[0], dates[1]);

            result.append(date1);
            result.append(" ");
            result.append(date2);
            result.append(" ");
            result.append(daysBetweenDates).append("\n");
        }


        textArea.setText(result.toString());
        result.setLength(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pickEndDate.setValue(LocalDate.parse("11.10.2017",formatter));
        pickStartDate.setValue(LocalDate.parse("18.10.2016",formatter));
        stayPeriod.setText("177");

        enterDate.setValue(LocalDate.parse("24.01.2017", formatter));
        exitDate.setValue(LocalDate.now());



        LocalDate[] dates = new LocalDate[2];

        dates[0] = LocalDate.parse("21.10.2016", formatter);
        dates[1] = LocalDate.parse("11.01.2017", formatter);

        dateObject.getEnterExitDate().add(dates);


        dates = new LocalDate[2];
        dates[0] = LocalDate.parse("24.01.2017", formatter);
        dates[1] = LocalDate.now();

        dateObject.getEnterExitDate().add(dates);
        updateTextArea();

    }
}
