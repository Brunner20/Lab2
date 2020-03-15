package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddWindow {
    private Controller controller;

    public AddWindow(Controller controller){
        this.controller=controller;
    }
    public void addTournament(){


        Label tourNameLa=new Label("название турнира");
        TextField tourName=new TextField();
        Label tourTypeLa=new Label("вид спорта");
        TextField tourType=new TextField("");
        Label tourDateLa=new Label("дата проведения");
        DatePicker tourDate = new DatePicker();
        Label tourWinnerLa=new Label("победитель турнира");
        Label tourWinnerLLa=new Label("имя");
        Label tourWinnerFLa=new Label("фамилия");
        Label tourWinnerMLa=new Label("отчество");
        TextField tourWinnerLast=new TextField();
        TextField tourWinnerFirst=new TextField();
        TextField tourWinnerMiddle=new TextField();
        Label tourPrizeLa=new Label("призовые");
        TextField tourPrize=new TextField();
        Button addBtn=new Button("добавить");

        ObservableList<Tournament> findList = FXCollections.observableArrayList();


        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!tourName.getText().equals("")&& !tourType.getText().equals("")&& !tourWinnerFirst.getText().equals("") &&
                        !tourWinnerLast.getText().equals("") && !tourWinnerMiddle.getText().equals("") && tourDate.getValue()!=null && tourPrize.getText()!=null)

                controller.addTournament(new Tournament(tourName.getText(),tourType.getText(),new Person(tourWinnerFirst.getText(),tourWinnerLast.getText(),
                        tourWinnerMiddle.getText()),tourDate.getValue(), Integer.parseInt(tourPrize.getText())));
                else System.out.println("некорректные данные");

            }
        });



        GridPane grid=new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(25);
        grid.setVgap(15);

        grid.add(tourNameLa,0,0);
        grid.add(tourName,1,0);

        grid.add(tourTypeLa,0,1);
        grid.add(tourType,1,1);

        grid.add(tourDateLa,0,2);
        grid.add(tourDate,1,2);

        grid.add(tourWinnerLa,0,3);
        grid.add(tourWinnerFLa,0,4);
        grid.add(tourWinnerLLa,0,5);
        grid.add(tourWinnerMLa,0,6);
        grid.add(tourWinnerFirst,1,4);
        grid.add(tourWinnerLast,1,5);
        grid.add(tourWinnerMiddle,1,6);

        grid.add(tourPrizeLa,0,8);
        grid.add(tourPrize,1,8);

        grid.add(addBtn,10,1,2,2);

        Scene scene = new Scene(grid,800,500);
        Stage newWindow = new Stage();
        newWindow.setTitle("добавить");
        newWindow.setScene(scene);
        newWindow.show();


    }
}
