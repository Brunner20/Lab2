package view;

import java.io.File;
import java.util.List;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class   MainWindow   {
    private Controller controller;
    private Stage stage;
    private TournamentTable table;


    public MainWindow(Controller controller,Stage stage){
        this.controller=controller;
        this.stage=stage;
        this.table=new TournamentTable(controller);


    }
    public  void show() {
        stage.setTitle("лаб2");
        Button loadBtn=new Button("загрузить");
        loadBtn.setLayoutY(230);
        loadBtn.setLayoutX(670);
        loadBtn.setMinSize(70,70);


        Button saveBtn=new Button("сохранить");
        saveBtn.setLayoutY(300);
        saveBtn.setLayoutX(670);
        saveBtn.setMinSize(70,70);


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
                AddWindow add =new AddWindow(controller,table);
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
                DeleteWindow del=new DeleteWindow(controller,table);
                del.delete();

            }
        });

        loadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("C:/Users/user/IdeaProjects/Lab2/src/view"));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    List loadReuslt =controller.load(file);
                    table.makeTable(loadReuslt);
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
                    controller.save(file);//"src/sample/work2.xml"


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
        Scene scene =new Scene(grid,1100,600);
        stage.setScene(scene);
        stage.show();
    }

}
