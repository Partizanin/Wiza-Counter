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
            dateObject.addEnterExitDate(enterDate.getValue(), exitDate.getValue());

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


            /*add data to table*/
        } else {
            /*count data and make result

            firstly need validation data,fields

            * todo at result must be: кількість днів які залишились до кінця візи. Кількість днів які ще можеш перебувати в Польщі. Кількість днів які провів провів вдома коли виїжджав. Кількість днів які провів в Польщі.*/

            dateObject.setStartDate(pickStartDate.getValue());
            dateObject.setEndDate(pickEndDate.getValue());
            dateObject.setStayPeriod(Integer.valueOf(stayPeriod.getText()));

            ReportGenerator reportGenerator = new ReportGenerator(dateObject);
            reportGenerator.saveAndOpenResultFile();
        }
    }


}
