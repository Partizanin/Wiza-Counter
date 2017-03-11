package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller {

    @FXML
    public DatePicker pickEndDate;
    @FXML
    public DatePicker pickStartDate;
    @FXML
    public TextField stayPeriod;
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
    public Button cleanUp;


    private StringBuilder result = new StringBuilder();


    private DateObject dateObject = new DateObject();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private Utils utils = new Utils();

    public Controller() {
        pickEndDate = new DatePicker();
        pickStartDate = new DatePicker();
        exitDate = new DatePicker();
        enterDate = new DatePicker();

    }

    public void buttonAction(ActionEvent actionEvent) {

        Button button = (Button) actionEvent.getSource();


        if (button.getText().equals("+")) {

            enterDate.setValue(enterDate.getConverter().fromString(enterDate.getEditor().getText()));
            exitDate.setValue(exitDate.getConverter().fromString(exitDate.getEditor().getText()));

            dateObject.addEnterExitDate(enterDate.getValue(), exitDate.getValue());

            updateTextArea();


        } else if (button.getText().equals("—")) {
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

        } else if (button.getText().equals("Очистити все")) {
            pickEndDate.getEditor().setText("");
            pickStartDate.getEditor().setText("");
            textArea.setText("");
            stayPeriod.setText("");
            enterDate.getEditor().setText("");
            exitDate.getEditor().setText("");
            dateObject = new DateObject();
        } else {

            pickEndDate.setValue(pickEndDate.getConverter().fromString(pickEndDate.getEditor().getText()));
            pickStartDate.setValue(pickStartDate.getConverter().fromString(pickStartDate.getEditor().getText()));

            dateObject.setStartDate(pickStartDate.getValue());
            dateObject.setEndDate(pickEndDate.getValue());
            dateObject.setStayPeriod(Integer.valueOf(stayPeriod.getText()));

            ReportGenerator reportGenerator = new ReportGenerator(dateObject);
            reportGenerator.saveAndOpenResultFile();
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


}
