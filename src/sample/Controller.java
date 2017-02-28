package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Controller {

    @FXML
    public DatePicker pickEndDate;
    @FXML
    public DatePicker pickStartDate;
    @FXML
    public DatePicker stainPeriod;
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
            String exitDateText = dateObject.getExitDate().get(dateObject.getExitDate().size()-1).format(formatter);

            result.append(textArea.getText());
            result.append(enterDateText);
            result.append(" ");
            result.append(exitDateText);
            result.append(" ");
            result.append(daysBetweenDate(enterDateText, exitDateText));
            result.append("\n");

/*1)95 днів перебування 177 - 95 = 92
* 2)39 днів перебування 92 - 39 = 53*/
            textArea.setText(result.toString());
            result.setLength(0);


            /*add data to table*/
        } else {
            /*count data and make result
            * todo at result must be: кількість днів які залишились до кінця візи. Кількість днів які ще можеш перебувати в польщі. Кількість днів які провів провів за кордоном коли виїжджав. Кількість днів які провів тут.*/


        }
    }

    public static void main(String[] args) {
        System.out.println(new Controller().daysBetweenDate("11.01.2017", "19.02.2017"));
        LocalDate date = LocalDate.now().plusDays(54);
        System.out.println(date);
    }
    private String daysBetweenDate(String sDate1, String sDate2) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


        LocalDate date = LocalDate.now();
        try {
            date = LocalDate.parse(sDate1, formatter);
        } catch (Exception e) {
            System.err.println(sDate1 + " have a bad format");
            e.printStackTrace();
        }

        LocalDate date2 = LocalDate.now();

        try {
            date2 = LocalDate.parse(sDate2, formatter);
        } catch (Exception e) {

            System.err.println(sDate2 + " have a bad format");
            e.printStackTrace();
        }

        long daysBetween = ChronoUnit.DAYS.between(date, date2);
        return String.valueOf(daysBetween);
    }

}
