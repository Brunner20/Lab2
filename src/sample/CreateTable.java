package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

  public class CreateTable {
      TableView<Tournament> table;
      public CreateTable(){
          table=new TableView<Tournament>();
      }
    public  void makeTable(ObservableList<Tournament> list) {



        TableColumn<Tournament, String> tournirName = new TableColumn<Tournament, String>("Название турнира");
        tournirName.setCellValueFactory(new PropertyValueFactory<>("nameOfTournament"));
        TableColumn<Tournament, String> tournirDate = new TableColumn<Tournament, String>("дата проведения");
        tournirDate.setCellValueFactory(new PropertyValueFactory<Tournament, String>("dateOfTournament"));
        TableColumn<Tournament, String> tournirSort = new TableColumn<Tournament, String>("вид спорта");
        tournirSort.setCellValueFactory(new PropertyValueFactory<Tournament, String>("typeOfSport"));
        TableColumn<Tournament, String> tournirWinnerF = new TableColumn<Tournament, String>("победитель имя");
        tournirWinnerF.setCellValueFactory(new PropertyValueFactory<Tournament, String>("firstname"));
        TableColumn<Tournament, String> tournirWinnerL = new TableColumn<Tournament, String>("победитель ф");
        tournirWinnerL.setCellValueFactory(new PropertyValueFactory<Tournament, String>("winner"));
        TableColumn<Tournament, String> tournirWinnerM = new TableColumn<Tournament, String>("победитель о");
        tournirWinnerM.setCellValueFactory(new PropertyValueFactory<Tournament, String>("winner"));
        TableColumn<Tournament, String> tournirPrize = new TableColumn<Tournament, String>("призовые");
        tournirPrize.setCellValueFactory(new PropertyValueFactory<Tournament, String>("prize"));
        TableColumn<Tournament, String> tournirIncome = new TableColumn<Tournament, String>("заработок");
        tournirIncome.setCellValueFactory(new PropertyValueFactory<Tournament, String>("income"));

        table.setItems(list);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getColumns().addAll(tournirName, tournirDate, tournirSort, tournirWinnerF,tournirWinnerL,tournirWinnerM, tournirPrize, tournirIncome);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }
        public  TableView<Tournament> getTable(){
            return this.table;
        }


}
