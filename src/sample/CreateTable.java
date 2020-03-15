package sample;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.util.Callback;


  public class CreateTable {

      TableView<Tournament> table;
      boolean used=false;
      Button nextPageBtn,previousPageBtn,lastPageBtn,firstPageBtn;
      int rowsPerPage=5;
      int pageNum =1;
      Label thisPage;

    public CreateTable(){
        table=new TableView<Tournament>();
}
    public  void makeTable(ObservableList<Tournament> list) {

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
                    return new ReadOnlyObjectWrapper<>(tournamentStringCellDataFeatures.getValue().getWinner().getFirstname());
                }
            });

            TableColumn<Tournament, String> tournirWinnerL = new TableColumn<Tournament, String>("Фамилия");
            tournirWinnerL.setCellValueFactory(new Callback<>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournament, String> tournamentStringCellDataFeatures) {
                    return new ReadOnlyObjectWrapper<>(tournamentStringCellDataFeatures.getValue().getWinner().getLastname());
                }
            });

            TableColumn<Tournament, String> tournirWinnerM = new TableColumn<Tournament, String>("Отчество");
            tournirWinnerM.setCellValueFactory(new Callback<>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Tournament, String> tournamentStringCellDataFeatures) {
                    return new ReadOnlyObjectWrapper<>(tournamentStringCellDataFeatures.getValue().getWinner().getMiddlename());
                }
            });
            tournirWinner.getColumns().addAll(tournirWinnerF, tournirWinnerL, tournirWinnerM);
            TableColumn<Tournament, String> tournirPrize = new TableColumn<Tournament, String>("призовые");
            tournirPrize.setCellValueFactory(new PropertyValueFactory<Tournament, String>("prize"));
            TableColumn<Tournament, String> tournirIncome = new TableColumn<Tournament, String>("заработок");
            tournirIncome.setCellValueFactory(new PropertyValueFactory<Tournament, String>("income"));

            ObservableList<Tournament> tourOnThisPage= FXCollections.observableArrayList();
            nextPageBtn=new Button(">");
            nextPageBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(tourOnThisPage.isEmpty()) {
                        if (pageNum + 1 <= list.size() / rowsPerPage) {
                            pageNum++;
                            //aaa
                        }
                    }else  if(pageNum + 1 <= tourOnThisPage.size() / rowsPerPage){
                        pageNum++;
                        //aaaa
                    }
                }
            });


            previousPageBtn=new Button("<");
            previousPageBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(tourOnThisPage.isEmpty()) {
                        if (pageNum -1>0) {
                            pageNum--;
                            //aaa
                        }
                    }else  if(pageNum -1>0){
                        pageNum--;
                        //aaaa
                    }
                }
            });

            table.getSelectionModel().setCellSelectionEnabled(true);
            table.getColumns().addAll(tournirName, tournirDate, tournirSort, tournirWinner, tournirPrize, tournirIncome);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            used = true;
        }
            table.setItems(list);
    }
        public  TableView<Tournament> getTable() {
            return this.table;
        }

        private void createPage(){

        }
}
