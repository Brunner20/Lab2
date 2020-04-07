package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Person;
import model.Tournament;

import java.util.List;

public class FindWindow {
    private Controller controller;
    private List<Tournament> searchResult;
    private TournamentTable table;

     public FindWindow(Controller controller){
         this.controller=controller;
         table=new TournamentTable(new Controller(FXCollections.observableArrayList()));
     }
    public  void findTournament()  {

        Label tourNameLa=new Label("название турнира");
        TextField tourName=new TextField();
        Button searchByNameOfTourBtn=new Button("искать по названию турнира");

        Label tourTypeLa=new Label("вид спорта");
        ComboBox<String> tourType=new ComboBox<String>();

        for(String type:controller.getListTypeOfSport() ){
            tourType.getItems().add(type);
        }
        Button searchByTypeBtn=new Button("искать по виду спорта");

        Label tourDateLa=new Label("дата проведения");
        DatePicker tourDate = new DatePicker();
        Button searchByDateBtn=new Button("искать по дате проведения");

        Label tourWinnerLa=new Label("победитель турнира ");
        Label tourWinnerFLa=new Label("имя ");
        Label tourWinnerLLa=new Label("фамилия ");
        Label tourWinnerMLa=new Label("отчество ");
        TextField tourWinnerFirst=new TextField();
        TextField tourWinnerLast=new TextField();
        TextField tourWinnerMiddle=new TextField();
        Button searchByWinner=new Button("искать по ФИО");

        Label tourPrizeLaLow=new Label("призовые       от");
        Label tourPrizeLaUpper=new Label("призовые       до");
        TextField tourPrizeLow=new TextField();
        TextField tourPrizeUpper=new TextField();
        Button searchByPrize=new Button("искать по призовым");

        Label tourIncomeLaLow=new Label("заработок      от");
        Label tourIncomeLaUpper=new Label("заработок    до");
        TextField tourIncomeLow=new TextField();
        TextField tourIncomeUpper=new TextField();
        Button searchByIncome=new Button(" искать по заработку");

        searchByNameOfTourBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByNameOfTournament(tourName.getText());
                table.makeTable(searchResult);
            }
        });
        searchByDateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByDateOfTournament(tourDate.getValue());
                table.makeTable(searchResult);
            }
        });
        searchByTypeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByTypeOfSport(tourType.getValue());
                table.makeTable(searchResult);

            }
        });
        searchByWinner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByWinnerOfTournament(new Person(tourWinnerLast.getText(),
                        tourWinnerFirst.getText(),tourWinnerMiddle.getText()));
                table.makeTable(searchResult);
            }
        });
        searchByPrize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByPrize(Integer.parseInt(tourPrizeLow.getText()), Integer.parseInt(tourPrizeUpper.getText()));
                table.makeTable(searchResult);
            }
        });
        searchByIncome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                searchResult=controller.findByIncome(Integer.parseInt(tourIncomeLow.getText()), Integer.parseInt(tourIncomeUpper.getText()));
                table.makeTable(searchResult);
            }
        });

        GridPane grid=new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(25);
        grid.setVgap(15);

        grid.add(tourNameLa,0,0);
        grid.add(tourName,1,0);
        grid.add(searchByNameOfTourBtn,2,0);

        grid.add(tourTypeLa,0,1);
        grid.add(tourType,1,1);
        grid.add(searchByTypeBtn,2,1);

        grid.add(tourDateLa,0,2);
        grid.add(tourDate,1,2);
        grid.add(searchByDateBtn,2,2);

        grid.add(tourWinnerLa,0,3);
        grid.add(tourWinnerFLa,0,4);
        grid.add(tourWinnerLLa,0,5);
        grid.add(tourWinnerMLa,0,6);
        grid.add(tourWinnerFirst,1,4);
        grid.add(tourWinnerLast,1,5);
        grid.add(tourWinnerMiddle,1,6);
        grid.add(searchByWinner,2,5);

        grid.add(tourPrizeLaLow,0,8);
        grid.add(tourPrizeLaUpper,0,9);
        grid.add(tourPrizeLow,1,8);
        grid.add(tourPrizeUpper,1,9);
        grid.add(searchByPrize,2,8);

        grid.add(tourIncomeLaLow,0,11);
        grid.add(tourIncomeLaUpper,0,12);
        grid.add(tourIncomeLow,1,11);
        grid.add(tourIncomeUpper,1,12);
        grid.add(searchByIncome,2,11);

        grid.add(table.getTable(),4,0,25,15);

        Scene scene = new Scene(grid,1200,600);
        Stage newWindow = new Stage();
        newWindow.setTitle("поиск");
        newWindow.setScene(scene);
        newWindow.show();


    }

}

