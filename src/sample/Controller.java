package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public TableView<Person> tableView = new TableView<>();
    @FXML
    public DatePicker enterDate;
    @FXML
    public DatePicker exitDate;
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person("A", "Z", "a@example.com"),
                    new Person("B", "X", "b@example.com"),
                    new Person("C", "W", "c@example.com"),
                    new Person("D", "Y", "d@example.com"),
                    new Person("E", "V", "e@example.com")
            );
    @FXML
    public TableColumn columnNumber;
    @FXML
    public TableColumn columnEnterDate;
    @FXML
    public TableColumn columnExitDate;
    @FXML
    public TableColumn columnPeriod;

    public Controller() {

    }


    public void tableSort(SortEvent<TableView> tableViewSortEvent) {

    }

    public void buttonAction(ActionEvent actionEvent) {

        initializeTable();

//        columnNumber = new TableColumn("№");
//        columnNumber.setCellValueFactory(new PropertyValueFactory<Person, String>("№"));
//        columnNumber.setPrefWidth(46);
//
//        columnEnterDate = new TableColumn("Last Name");
//        columnEnterDate.setCellValueFactory(
//                new PropertyValueFactory<Person, String>("Дата вїзду"));
//        columnEnterDate.setPrefWidth(153.0);
//
//        columnExitDate = new TableColumn("Дата виїзду");
//        columnExitDate.setMinWidth(9.0);
//        columnExitDate.setPrefWidth(140);
//        columnExitDate.setCellValueFactory(
//                new PropertyValueFactory<Person, String>("Дата виїзду"));
//
//        columnPeriod = new TableColumn("Інтервал");
//        columnExitDate.setPrefWidth(82.0);
//        columnExitDate.setCellValueFactory(
//                new PropertyValueFactory<Person, String>("Дата виїзду"));
//
//        tableView.setItems(data);
//        tableView.getColumns().addAll(columnNumber, columnEnterDate, columnExitDate, columnPeriod);


        Button button = (Button) actionEvent.getSource();

        if (enterDate.getValue() != null) {
            String enterDateText = enterDate.getValue().toString();
        }

        if (exitDate.getValue() != null) {
            String exitDateText = exitDate.getValue().toString();
        }


        if (button.getText().equals("+")) {



            /*add data to table*/
        } else {
            /*count data and make result*/
        }
    }

    private void initializeTable() {


        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName")
        );

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email")
        );

        tableView.setItems(data);
        tableView.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

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
        System.out.println(daysBetween);
        return String.valueOf(daysBetween);
    }

}
