package sample;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

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

    private Tooltip infoTt = new Tooltip();
    private Tooltip errorTt = new Tooltip();
    private PauseTransition delayError = new PauseTransition(Duration.seconds(3));
    private PauseTransition delayInfoTt = new PauseTransition(Duration.seconds(3));

    public Controller() {
        infoTt.setAutoHide(true);
        infoTt.getStyleClass().add("tooltipInfo");
        delayInfoTt.setOnFinished(event -> infoTt.hide());

        errorTt.setAutoHide(true);
        errorTt.getStyleClass().add("tooltipError");
        delayError.setOnFinished(event -> errorTt.hide());

    }

    private void showErrorMessage(String value) {
        errorTt.setText(value);
        errorTt.show(textArea, 0, 0);
        errorTt.centerOnScreen();
        delayError.play();
    }

    private void showInfoMessage(String stringValue) {
        infoTt.setText(stringValue);
        infoTt.show(textArea, 0, 0);
        infoTt.centerOnScreen();
        delayInfoTt.play();
    }

    public void buttonAction(ActionEvent actionEvent) {

        Button button = (Button) actionEvent.getSource();

        boolean allFiledIsValid = true;
        boolean enterDateValid = true;
        boolean exitDateValid = true;

        if (textArea.getStyleClass().size() > 0) {
            textArea.getStyleClass().remove("redBorder");
        }

        if (button.getText().equals("+")) {
            String enterDateValidationResult = utils.dateValidation(this.enterDate.getEditor().getText());
            String exitDateValidationResult = utils.dateValidation(exitDate.getEditor().getText());

            if (!enterDateValidationResult.equals("x")) {

                if (this.enterDate.getStyleClass().size() > 0) {
                    this.enterDate.getStyleClass().remove("redBorder");
                }

                this.enterDate.setValue(this.enterDate.getConverter().fromString(enterDateValidationResult));

            } else {
                enterDateValid = false;

                if (this.enterDate.getStyleClass().size() < 3) {
                    this.enterDate.getStyleClass().add("redBorder");
                }
                showErrorMessage("Дата в'їзду введене не корректно!");
            }

            if (!exitDateValidationResult.equals("x")) {

                if (exitDate.getStyleClass().size() > 0) {
                    exitDate.getStyleClass().remove("redBorder");
                }

                exitDate.setValue(exitDate.getConverter().fromString(exitDateValidationResult));

            } else {
                exitDateValid = false;

                if (exitDate.getStyleClass().size() < 3) {
                    exitDate.getStyleClass().add("redBorder");
                }
                showErrorMessage("Дату виїзду введене не корректно!");
            }

            if (enterDateValid && exitDateValid) {

                String addingResult = dateObject.addEnterExitDate(this.enterDate.getValue(), exitDate.getValue());

                if (addingResult.equals("Дати успішно додано!")) {
                    updateTextArea();
                    showInfoMessage(addingResult);
                } else {
                    showErrorMessage(addingResult);
                }
            }


        } else if (button.getText().equals("—")) {
            String enterDateValidationResult = utils.dateValidation(this.enterDate.getEditor().getText());
            String exitDateValidationResult = utils.dateValidation(exitDate.getEditor().getText());

            if (!enterDateValidationResult.equals("x") && !exitDateValidationResult.equals("x")) {

                this.enterDate.setValue(this.enterDate.getConverter().fromString(utils.dateValidation(this.enterDate.getEditor().getText())));
                exitDate.setValue(exitDate.getConverter().fromString(exitDate.getEditor().getText()));

                LocalDate enterDateValue = this.enterDate.getValue();
                LocalDate exitDateValue = exitDate.getValue();

                if (dateObject.getEnterExitDate().size() > 0) {
                    boolean isFound = false;
                    for (int i = 0; i < dateObject.getEnterExitDate().size(); i++) {
                        LocalDate[] dates = dateObject.getEnterExitDate().get(i);
                        LocalDate enterDate = dates[0];
                        LocalDate exitDate = dates[1];

                        if (enterDate.isEqual(enterDateValue)) {

                            if (exitDate.isEqual(exitDateValue)) {
                                isFound = true;
                                dateObject.getEnterExitDate().remove(i);
                                updateTextArea();
                                showInfoMessage("Дати " + enterDateValue.format(formatter) + " - " + exitDateValue.format(formatter) + " видалено!");
                                break;
                            }
                        }

                    }
                    if (!isFound) {
                        showErrorMessage("Дати " + enterDateValue.format(formatter) + " - " + exitDateValue.format(formatter) + " не знайдено!");
                        if (textArea.getStyleClass().size() < 3) {
                            textArea.getStyleClass().addAll("redBorder");
                        }
                    }
                } else {

                    showInfoMessage("Список порожній,всі дати видалені!");
                }
            } else {
                showErrorMessage("enter date or exit date is not valid");
                System.out.println("enter date or exit date is not valid");
            }

        } else if (button.getText().equals("Очистити все")) {
            pickEndDate.getEditor().setText("");
            pickStartDate.getEditor().setText("");
            textArea.setText("");
            stayPeriod.setText("");
            this.enterDate.getEditor().setText("");
            exitDate.getEditor().setText("");
            dateObject = new DateObject();
            showInfoMessage("Все очищено!");
        } else {
            String endDateValidationResult = utils.dateValidation(pickEndDate.getEditor().getText());
            String startDateValidationResult = utils.dateValidation(pickStartDate.getEditor().getText());


            if (!endDateValidationResult.equals("x")) {


                if (stayPeriod.getStyleClass().size() > 0) {
                    stayPeriod.getStyleClass().remove("redBorder");
                }

                if (pickEndDate.getStyleClass().size() > 0) {
                    pickEndDate.getStyleClass().remove("redBorder");
                }


                pickEndDate.setValue(pickEndDate.getConverter().fromString(endDateValidationResult));

                dateObject.setEndDate(pickEndDate.getValue());

                if (!utils.numberValidation(stayPeriod.getText()).equals("x")) {
                    dateObject.setStayPeriod(Integer.valueOf(stayPeriod.getText()));


                } else {
                    allFiledIsValid = false;
                    if (stayPeriod.getStyleClass().size() < 3) {
                        stayPeriod.getStyleClass().addAll("redBorder");
                    }
                    String errorMessage = "Період перебування введене не вірно!";

                    if (stayPeriod.getText().length() <= 0) {
                        errorMessage = "Період перебування не введено!";
                    }
                    showErrorMessage(errorMessage);
                }

            } else {
                allFiledIsValid = false;

                if (pickEndDate.getStyleClass().size() < 3) {

                    pickEndDate.getStyleClass().addAll("redBorder");
                }
                showErrorMessage("Дата введене не коректно");
            }
            if (!startDateValidationResult.equals("x")) {

                if (pickStartDate.getStyleClass().size() > 0) {
                    pickStartDate.getStyleClass().remove("redBorder");
                }


                pickStartDate.setValue(pickStartDate.getConverter().fromString(startDateValidationResult));

                dateObject.setStartDate(pickStartDate.getValue());

            } else {
                allFiledIsValid = false;
                if (pickStartDate.getStyleClass().size() < 3) {
                    pickStartDate.getStyleClass().addAll("redBorder");
                }
                showErrorMessage("Дата введене не коректно");
            }

            if (dateObject.isHaveNotNullFiled(dateObject) && allFiledIsValid) {
                ReportGenerator reportGenerator = new ReportGenerator(dateObject);
                reportGenerator.saveAndOpenResultFile();
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
        pickStartDate.setValue(LocalDate.parse("18.10.2016", formatter));
        pickEndDate.setValue(LocalDate.parse("11.10.2017", formatter));
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
