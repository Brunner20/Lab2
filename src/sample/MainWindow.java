package sample;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class   MainWindow   {
    private Controller controller;
    private Stage stage;
    CreateTable table;


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
                   find.findTournament();
            }
        });

        Button addBtn=new Button("добавить");
        addBtn.setLayoutY(280);
        addBtn.setLayoutX(600);
        addBtn.setMinSize(70,70);
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AddWindow add =new AddWindow(controller);
                add.addTournament();
            }
        });
        Button deleteBtn=new Button("удаление");
        deleteBtn.setLayoutY(350);
        deleteBtn.setLayoutX(600);
        deleteBtn.setMinSize(70,70);
        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DeleteWindow del=new DeleteWindow(controller);
                del.show();
            }
        });

        loadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("C:/Users/user/IdeaProjects/Lab2/src/sample"));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    controller.load("src/sample/" + file.getName());
                    table.makeTable(controller.getTournaments());
                }
            }
        });
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("C:/Users/user/IdeaProjects/Lab2/src/sample"));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showSaveDialog(stage);
                if (file != null) {
                    controller.save("src/sample/" + file.getName());//"src/sample/work2.xml"


                }
            }
        });
        GridPane grid=new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(25);
        grid.setVgap(15);
        grid.add(loadBtn,0,0);
        grid.add(saveBtn,0,1);
        grid.add(findBtn,0,2);
        grid.add(deleteBtn,0,3);
        grid.add(addBtn,0,4);
        grid.add(table.getTable(),2,0,20,5);
        Scene scene =new Scene(grid,800,600);
        stage.setScene(scene);
        stage.show();
    }

    public void update(){

    }
}
