package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    private ReportGenerator reportGenerator = new ReportGenerator();

    private Utils utils = new Utils();

    public Controller() {

    }

    public void buttonAction(ActionEvent actionEvent) {

        Button button = (Button) actionEvent.getSource();

        if (enterDate.getValue() != null) {

            dateObject.getEnterDate().add(enterDate.getValue());
        }

        if (exitDate.getValue() != null) {

            dateObject.getExitDate().add(exitDate.getValue());
        }


        if (button.getText().equals("+")) {
/*

            for (int i = 0; i < dateObject.getEnterDate().size(); i++) {
                String enterDateText = dateObject.getEnterDate().get(i).format(formatter);
                String exitDateText = dateObject.getExitDate().get(i).format(formatter);

                result.append(enterDateText).append(" ").append(exitDateText).append(" ").append(daysBetweenDate(enterDateText, exitDateText)).append("\n");
            }
*/

            String enterDateText = dateObject.getEnterDate().get(dateObject.getEnterDate().size()-1).format(formatter);
            String exitDateText = dateObject.getExitDate().get(dateObject.getExitDate().size() - 1).format(formatter);


            result.append(textArea.getText());
            result.append(enterDateText);
            result.append(" ");
            result.append(exitDateText);
            result.append(" ");
            result.append(utils.daysBetweenDate(dateObject.getEnterDate().get(dateObject.getEnterDate().size()-1),dateObject.getExitDate().get(dateObject.getExitDate().size() - 1)));
            result.append("\n");


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

            reportGenerator = new ReportGenerator(dateObject);
            reportGenerator.saveAndOpenResultFile();
        }
    }


}
