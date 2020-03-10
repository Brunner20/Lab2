package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class   MainWindow {

    public static void show(Stage stage,String path, double sX,double sY) throws IOException, SAXException, ParserConfigurationException {

        Button findBtn=new Button("поиск");
        findBtn.setLayoutY(210);
        findBtn.setLayoutX(600);
        Button addBtn=new Button("добавить");
        addBtn.setLayoutY(240);
        addBtn.setLayoutX(600);
        Button deleteBtn=new Button("удаление");
        deleteBtn.setLayoutY(270);
        deleteBtn.setLayoutX(600);
        CreateTable mainTable=new CreateTable();
        Group group=new Group();
        SaxParser saxForFind=new SaxParser();
        group.getChildren().addAll(mainTable.makeTable(saxForFind.parse(path)),findBtn,deleteBtn,addBtn);
        Scene scene =new Scene(group,sX,sY);
        stage.setScene(scene);
        findBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FindWindow.show();
            }
        });
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AddWindow.show();
            }
        });

        stage.show();
    }
}
