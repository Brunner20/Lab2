package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

  public class CreateTable {
    public static Group getGroup(){


        TableView<Tournament> table= new TableView<Tournament>();
        TableColumn<Tournament,String> tournirName = new TableColumn<Tournament,String>("Название турнира");
        tournirName.setCellValueFactory(new PropertyValueFactory<>("nameOfTournament"));
        TableColumn<Tournament,String> tournirDate = new TableColumn<Tournament,String>("дата проведения");
        tournirDate.setCellValueFactory(new PropertyValueFactory<Tournament, String>("dateOfTournament"));
        TableColumn<Tournament,String> tournirSort = new TableColumn<Tournament,String>("дата проведения");
        tournirSort.setCellValueFactory(new PropertyValueFactory<Tournament, String>("typeOfSport"));
        TableColumn<Tournament,String> tournirWinner = new TableColumn<Tournament,String>("победитель");
        tournirWinner.setCellValueFactory(new PropertyValueFactory<Tournament, String>("name"));
        TableColumn<Tournament,String> tournirPrize = new TableColumn<Tournament,String>("призовые");
        tournirPrize.setCellValueFactory(new PropertyValueFactory<Tournament, String>("prize"));
        TableColumn<Tournament,String> tournirIncome = new TableColumn<Tournament,String>("заработок");
        tournirIncome.setCellValueFactory(new PropertyValueFactory<Tournament, String>("income"));

        table.setItems(SaxParser.getList());
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getColumns().add(tournirName);
        table.getColumns().add(tournirDate);
        table.getColumns().add(tournirSort);
        table.getColumns().add(tournirWinner);
        table.getColumns().add(tournirPrize);
        table.getColumns().add(tournirIncome);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Group group= new Group(table);
        return group;
    }
}