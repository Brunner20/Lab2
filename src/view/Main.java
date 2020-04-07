package view;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Tournament;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage){
        List<Tournament> list =new ArrayList<Tournament>();
        Controller cont= new Controller(list);
        MainWindow mainWindow=new MainWindow(cont,stage);
        mainWindow.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
