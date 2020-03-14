package sample;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class   MainWindow   {
    private Controller controller;
    private Stage stage;
    private CreateTable table;


    public MainWindow(Controller controller){
        this.controller=controller;
        stage=new Stage();
        table=new CreateTable();

    }
    public  void show() throws IOException, SAXException, ParserConfigurationException {
        stage.setTitle("лаб2");
        Button loadBtn=new Button("загрузить");
        loadBtn.setLayoutY(230);
        loadBtn.setLayoutX(670);
        loadBtn.setMinSize(70,70);


        Button saveBtn=new Button("сохранить");
        saveBtn.setLayoutY(300);
        saveBtn.setLayoutX(670);
        saveBtn.setMinSize(70,70);
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.save("src/sample/work2.xml");
            }
        });

        Button findBtn=new Button("поиск");
        findBtn.setLayoutY(210);
        findBtn.setLayoutX(600);
        findBtn.setMinSize(70,70);
        findBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                   FindWindow find = new FindWindow(controller);
                   find.show();
            }
        });

        Button addBtn=new Button("добавить");
        addBtn.setLayoutY(280);
        addBtn.setLayoutX(600);
        addBtn.setMinSize(70,70);
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // AddWindow.show();
            }
        });
        Button deleteBtn=new Button("удаление");
        deleteBtn.setLayoutY(350);
        deleteBtn.setLayoutX(600);
        deleteBtn.setMinSize(70,70);

        loadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("C:/Users/user/IdeaProjects/Lab2/src/sample"));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    controller.load("src/sample/" + file.getName());//"src/sample/work2.xml"
                    table.makeTable(controller.getTournaments());
                    table.getTable().refresh();
                }
            }
        });

        Group group=new Group();
        group.getChildren().addAll(table.getTable(),findBtn,deleteBtn,addBtn,saveBtn,loadBtn);
        Scene scene =new Scene(group,800,600);
        stage.setScene(scene);
        stage.show();
    }

    public void update(){

    }
}
