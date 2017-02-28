package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Дата до кінця візи");
        primaryStage.setScene(new Scene(root, 439, 465));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void dateOfEndWiza(){

        LocalDate date = LocalDate.now();

        date = date.plusDays(82);
        System.out.println(date);

    }

}
