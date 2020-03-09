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


        SaxParser sax=new SaxParser("src/sample/work.xml");
        Scene scene =new Scene(CreateTable.getGroup(),800,800);
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
