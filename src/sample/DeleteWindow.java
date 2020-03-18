package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeleteWindow {
    private Controller controller;
    private ObservableList<Tournament> listForDelete;
    private TournamentTable table;
    private Stage newWindow;

    public DeleteWindow(Controller controller,TournamentTable table){
        this.controller=controller;
        this.table=table;
        this.newWindow=new Stage();
    }
    public void delete() {
        Label tourNameLa=new Label("название турнира");
        TextField tourName=new TextField();
        Button delByNameOfTourBtn=new Button("удалить по названию турнира");

        Label tourTypeLa=new Label("вид спорта");
        ComboBox<String> tourType=new ComboBox<String>();

        for(Tournament tour:controller.getTournaments() ){
            System.out.println(tour.getTypeOfSport());
            tourType.getItems().add(tour.getTypeOfSport());
        }
        Button delByTypeBtn=new Button("удалить по виду спорта");

        Label tourDateLa=new Label("дата проведения");
        DatePicker tourDate = new DatePicker();
        Button delByDateBtn=new Button("удалить по дате проведения");

        Label tourWinnerLa=new Label("победитель турнира ");
        Label tourWinnerFLa=new Label("имя ");
        Label tourWinnerLLa=new Label("фамилия ");
        Label tourWinnerMLa=new Label("отчество ");
        TextField tourWinnerFirst=new TextField();
        TextField tourWinnerLast=new TextField();
        TextField tourWinnerMiddle=new TextField();
        Button delByWinner=new Button("удалить по ФИО");

        Label tourPrizeLaLow=new Label("призовые       от");
        Label tourPrizeLaUpper=new Label("призовые       до");
        TextField tourPrizeLow=new TextField();
        TextField tourPrizeUpper=new TextField();
        Button delByPrize=new Button("удалить по призовым");

        Label tourIncomeLaLow=new Label("заработок      от");
        Label tourIncomeLaUpper=new Label("заработок    до");
        TextField tourIncomeLow=new TextField();
        TextField tourIncomeUpper=new TextField();
        Button delByIncome=new Button(" удалить по заработку");

        delByNameOfTourBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listForDelete=controller.findByNameOfTournament(tourName.getText());
                controller.deleteTournament(listForDelete);
                dialog(listForDelete.size());
                table.updateTable();
                newWindow.close();
            }
        });
        delByDateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listForDelete = controller.findByDateOfTournament(tourDate.getValue());
                controller.deleteTournament(listForDelete);
                dialog(listForDelete.size());
                table.updateTable();
                newWindow.close();
            }
        });
        delByTypeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listForDelete=controller.findByTypeOfSport(tourType.getValue());
                controller.deleteTournament(listForDelete);
                dialog(listForDelete.size());
                table.updateTable();
                newWindow.close();
            }
        });
        delByWinner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listForDelete=controller.findByWinnerOfTournament(new Person(tourWinnerLast.getText(),
                        tourWinnerFirst.getText(),tourWinnerMiddle.getText()));
                controller.deleteTournament(listForDelete);
                dialog(listForDelete.size());
                table.updateTable();
                newWindow.close();
            }
        });
        delByPrize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listForDelete=controller.findByPrize(Integer.parseInt(tourPrizeLow.getText()), Integer.parseInt(tourPrizeUpper.getText()));
                controller.deleteTournament(listForDelete);
                dialog(listForDelete.size());
                table.updateTable();
                newWindow.close();
            }
        });
        delByIncome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listForDelete=controller.findByIncome(Integer.parseInt(tourIncomeLow.getText()), Integer.parseInt(tourIncomeUpper.getText()));
                controller.deleteTournament(listForDelete);
                dialog(listForDelete.size());
                table.updateTable();
                newWindow.close();
                }
        });

        GridPane grid=new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(25);
        grid.setVgap(15);

        grid.add(tourNameLa,0,0);
        grid.add(tourName,1,0);
        grid.add(delByNameOfTourBtn,2,0);

        grid.add(tourTypeLa,0,1);
        grid.add(tourType,1,1);
        grid.add(delByTypeBtn,2,1);

        grid.add(tourDateLa,0,2);
        grid.add(tourDate,1,2);
        grid.add(delByDateBtn,2,2);

        grid.add(tourWinnerLa,0,3);
        grid.add(tourWinnerFLa,0,4);
        grid.add(tourWinnerLLa,0,5);
        grid.add(tourWinnerMLa,0,6);
        grid.add(tourWinnerFirst,1,4);
        grid.add(tourWinnerLast,1,5);
        grid.add(tourWinnerMiddle,1,6);
        grid.add(delByWinner,2,5);

        grid.add(tourPrizeLaLow,0,8);
        grid.add(tourPrizeLaUpper,0,9);
        grid.add(tourPrizeLow,1,8);
        grid.add(tourPrizeUpper,1,9);
        grid.add(delByPrize,2,8);

        grid.add(tourIncomeLaLow,0,11);
        grid.add(tourIncomeLaUpper,0,12);
        grid.add(tourIncomeLow,1,11);
        grid.add(tourIncomeUpper,1,12);
        grid.add(delByIncome,2,11);

        Scene scene = new Scene(grid,800,600);

        newWindow.setTitle("поиск");
        newWindow.setScene(scene);
        newWindow.show();

    }
    private void dialog(int counter){
        Label deleted=new Label();
        Label count =new Label();
        GridPane grid =new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.add(deleted,0,0);
        grid.add(count,1,0);
        if(counter!=0){
            deleted.setText("Было удалено");
            count.setText( String.valueOf(counter));
        }
        else{
            deleted.setText(" команд для удаления не обнаружено");
        }
        Scene scene = new Scene(grid,300,150);
        Stage dialogWindow =new Stage();
        dialogWindow.setTitle("результат удаления");
        dialogWindow.setScene(scene);
        dialogWindow.show();

    }
}
