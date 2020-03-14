package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Tournament;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        ObservableList<Tournament> list =FXCollections.observableArrayList();
        Controller cont= new Controller(list);
        MainWindow mainWindow=new MainWindow(cont);
        mainWindow.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
