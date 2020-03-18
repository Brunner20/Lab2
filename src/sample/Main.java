package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage){
        ObservableList<Tournament> list =FXCollections.observableArrayList();
        Controller cont= new Controller(list);
        MainWindow mainWindow=new MainWindow(cont);
        mainWindow.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
