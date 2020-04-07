package view;

import controller.Controller;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Tournament;

import java.util.List;


public class TournamentTable {

      private TableView<Tournament> table;
      private Controller controller;
      private boolean used=false;
      private Button nextPageBtn,previousPageBtn,lastPageBtn,firstPageBtn,choiceBtn;
      private int rowsPerPage=3;
      private int thisPageNumber =1;
      private int lastPage;
      private GridPane grid;
      private Label thisPageLabel,descriptionLabel;
      private TextField rowsChoice;

    public TournamentTable(Controller controller){
        this.used=false;
        this.table=new TableView<Tournament>();
        this.controller=controller;
        this.grid=new GridPane();
        this.rowsChoice=new TextField();
        this.descriptionLabel=new Label();
        this.thisPageLabel=new Label();

}
      public void makeTable(List<Tournament> list){

        controller=new Controller(list);
          pagination();
          makePage(controller.getPage(thisPageNumber,rowsPerPage));

      }
    private  void makePage(List<Tournament> list) {

        if(!used) {
            TableColumn<Tournament, String> tournirName = new TableColumn<Tournament, String>("Название турнира");
            tournirName.setCellValueFactory(new PropertyValueFactory<>("nameOfTournament"));
            TableColumn<Tournament, String> tournirDate = new TableColumn<Tournament, String>("дата проведения");
            tournirDate.setCellValueFactory(new PropertyValueFactory<Tournament, String>("dateOfTournament"));
            TableColumn<Tournament, String> tournirSort = new TableColumn<Tournament, String>("вид спорта");
            tournirSort.setCellValueFactory(new PropertyValueFactory<Tournament, String>("typeOfSport"));
            TableColumn<Tournament, String> tournirWinner = new TableColumn<Tournament, String>("победитель турнира");
            TableColumn<Tournament, String> tournirWinnerF = new TableColumn<Tournament, String>("Имя");
            tournirWinnerF.setCellValueFactory(new Callback<>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournament, String> tournamentStringCellDataFeatures) {
                    return new ReadOnlyObjectWrapper<>(tournamentStringCellDataFeatures.getValue().getWinner().getFirstName());
                }
            });

            TableColumn<Tournament, String> tournirWinnerL = new TableColumn<Tournament, String>("Фамилия");
            tournirWinnerL.setCellValueFactory(new Callback<>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournament, String> tournamentStringCellDataFeatures) {
                    return new ReadOnlyObjectWrapper<>(tournamentStringCellDataFeatures.getValue().getWinner().getLastName());
                }
            });

            TableColumn<Tournament, String> tournirWinnerM = new TableColumn<Tournament, String>("Отчество");
            tournirWinnerM.setCellValueFactory(new Callback<>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournament, String> tournamentStringCellDataFeatures) {
                    return new ReadOnlyObjectWrapper<>(tournamentStringCellDataFeatures.getValue().getWinner().getMiddleName());
                }
            });
            tournirWinner.getColumns().addAll(tournirWinnerF, tournirWinnerL, tournirWinnerM);
            TableColumn<Tournament, String> tournirPrize = new TableColumn<Tournament, String>("призовые");
            tournirPrize.setCellValueFactory(new PropertyValueFactory<Tournament, String>("prize"));
            TableColumn<Tournament, String> tournirIncome = new TableColumn<Tournament, String>("заработок");
            tournirIncome.setCellValueFactory(new PropertyValueFactory<Tournament, String>("income"));
            grid.setPadding(new Insets(20));
            grid.setHgap(25);
            grid.setVgap(15);
            grid.add(this.table,0,0,16,5);
            grid.add(this.firstPageBtn,8,6);
            grid.add(this.previousPageBtn,9,6);
            grid.add(this.thisPageLabel,10,6);
            grid.add(this.nextPageBtn,11,6);
            grid.add(this.lastPageBtn,12,6);
            grid.add(this.rowsChoice,1,6);
            grid.add(this.choiceBtn,2,6);
            grid.add(this.descriptionLabel,0,6);
            table.getColumns().addAll(tournirName, tournirDate, tournirSort, tournirWinner, tournirPrize, tournirIncome);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            used = true;
        }
            ObservableList<Tournament> addList= FXCollections.observableArrayList(list);
            table.setItems(addList);
    }


        public void pagination(){

            thisPageNumber=1;
            lastPage= controller.getLastPage(rowsPerPage);
            descriptionLabel.setText(controller.getSize()+" записей разбить по");
            choiceBtn=new Button("Разбить");
            choiceBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                try {
                        rowsPerPage=Integer.parseInt(rowsChoice.getText());
                        thisPageNumber=1;
                        makePage(controller.getPage(thisPageNumber, rowsPerPage));
                        lastPage= controller.getLastPage(rowsPerPage);
                        thisPageLabel.setText(thisPageNumber+"/"+lastPage);
                    }
                    catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Введите число");
                        alert.showAndWait();
                    }
                }
            });

            nextPageBtn=new Button(">");
            nextPageBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    if (thisPageNumber + 1 <= lastPage) {
                        thisPageNumber++;
                        makePage(controller.getPage(thisPageNumber, rowsPerPage));
                        thisPageLabel.setText(thisPageNumber+"/"+lastPage);
                    }
                }
            });

            previousPageBtn=new Button("<");
            previousPageBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    if (thisPageNumber -1>0) {
                        thisPageNumber--;
                        makePage(controller.getPage(thisPageNumber, rowsPerPage));
                        thisPageLabel.setText(thisPageNumber+"/"+lastPage);
                    }
                }
            });
            firstPageBtn=new Button("<<");
            firstPageBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    if (thisPageNumber !=1) {
                        thisPageNumber=1;
                        makePage(controller.getPage(thisPageNumber, rowsPerPage));
                        thisPageLabel.setText(thisPageNumber+"/"+lastPage);
                    }
                }
            });
            lastPageBtn=new Button(">>");
            lastPageBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    if (thisPageNumber !=lastPage) {
                        thisPageNumber=lastPage;
                        makePage(controller.getPage(thisPageNumber, rowsPerPage));
                        thisPageLabel.setText(thisPageNumber+"/"+lastPage);
                    }
                }
            });
            thisPageLabel.setText(thisPageNumber+"/"+lastPage);

        }

      public  GridPane getTable() {
          return this.grid;
      }

}

