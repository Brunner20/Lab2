package sample;

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

        MainWindow.show(stage,"src/sample/work2.xml",800,800);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
