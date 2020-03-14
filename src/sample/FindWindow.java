package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;


public class FindWindow {
    private Controller controller;
    private ObservableList<Tournament> searchResult;

     public FindWindow(Controller controller){
         this.controller=controller;
     }
    public  void show()  {


        Label tourNameLa=new Label("название турнира");
        TextField tourName=new TextField();
        Button searchByNameOfTourBtn=new Button("искать по названию турнира");

        Label tourTypeLa=new Label("вид спорта");
        ComboBox<String> tourType=new ComboBox<String>();
        for(Tournament tour: controller.getTournaments()){
            tourType.setValue(tour.getTypeOfSport());
        }
        Button searchByTypeBtn=new Button("искать виду спорта");

        Label tourDateLa=new Label("дата проведения");
        DatePicker tourDate = new DatePicker();
        Button searchByDateBtn=new Button("искать по дате проведения");

        Label tourWinnerLa=new Label("победитель турнира ФИО");
        TextField tourWinnerFirst=new TextField();
        TextField tourWinnerLast=new TextField();
        TextField tourWinnerMiddle=new TextField();
        Button searchByWinner=new Button("иcкать по ФИО");

        Label tourPrizeLa=new Label("призовые");
        TextField tourPrizeLow=new TextField();
        TextField tourPrizeUpper=new TextField();
        Button searchByPrize=new Button("искать по призовым");

        Label tourIncomeLa=new Label("заработок");
        TextField tourIncomeLow=new TextField();
        TextField tourIncomeUpper=new TextField();
        Button searchByIncome=new Button(" искать по заработку");

        CreateTable table=new CreateTable();


        searchByNameOfTourBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByNameOfTournament(tourName.getText());
            }
        });
        searchByDateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByDateOfTournament(tourDate.getValue());
            }
        });
        searchByTypeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByTypeOfSport(tourType.getValue());
            }
        });
        searchByWinner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByWinnerOfTournament(new Person(tourWinnerLast.getText(),
                        tourWinnerFirst.getText(),tourWinnerMiddle.getText()));
            }
        });
        searchByPrize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByPrize(Integer.parseInt(tourPrizeLow.getText()), Integer.parseInt(tourPrizeUpper.getText()));
            }
        });
        searchByIncome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByIncome(Integer.parseInt(tourIncomeLow.getText()), Integer.parseInt(tourIncomeUpper.getText()));
            }
        });




        table.makeTable(searchResult);
        FlowPane pane = new FlowPane(Orientation.VERTICAL, 10, 10);
        pane.getChildren().addAll(tourName,tourDate,tourType,tourWinnerFirst,tourWinnerLast,tourWinnerMiddle,tourPrizeLow,
                tourPrizeUpper,tourIncomeLow,tourIncomeUpper,searchByDateBtn,searchByIncome,searchByNameOfTourBtn,searchByPrize,
                searchByTypeBtn,searchByWinner);

        FlowPane pane1=new FlowPane(Orientation.HORIZONTAL, 10, 10);
        pane1.getChildren().addAll(pane,table.getTable());
        pane1.setPadding(new Insets(20));
        Scene scene = new Scene(pane1,800,300);
        Stage newWindow = new Stage();
        newWindow.setTitle("поиск");
        newWindow.setScene(scene);
        newWindow.show();


    }

}

