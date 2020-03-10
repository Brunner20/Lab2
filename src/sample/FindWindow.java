package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class FindWindow {
    public static void show()  {


        SaxParser forBox=new SaxParser();
       ObservableList<Tournament> list= forBox.parse("src/sample/work2.xml");
       ObservableList <String> type =FXCollections.observableArrayList();
        ComboBox<String> tourType=new ComboBox<String>();
       for(Tournament tour: list)
           tourType.getItems().add(tour.getTypeOfSport());


        TextField tourName=new TextField("название турнира");
        DatePicker tourDate = new DatePicker();
        TextField tourWinner=new TextField("победитель турнира");
        TextField tourPrize=new TextField("10");
        TextField tourIncome=new TextField("размер дохода победителя турнира");
        Button findBtn= new Button("найти");

        ObservableList <Tournament> findList =FXCollections.observableArrayList();
        CreateTable d=new CreateTable();
        TableView<Tournament> table=d.makeTable(findList);

        findBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Tournament findSample=new Tournament(tourName.getText(),tourType.getValue(), tourWinner.getText(),
                        tourDate.getValue(),Integer.parseInt(tourPrize.getText()));

                    table.setItems(FindRows.find("src/sample/work2.xml",findSample));



            }
        });


        FlowPane pane = new FlowPane(Orientation.VERTICAL, 10, 10);
        pane.getChildren().addAll(tourName,tourDate,tourType,tourWinner,tourPrize,tourIncome,findBtn);
        FlowPane pane1=new FlowPane(Orientation.HORIZONTAL, 10, 10);
        pane1.getChildren().addAll(pane,table);
        pane.setPadding(new Insets(20));
        Scene scene = new Scene(pane1,800,300);
        Stage newWindow = new Stage();
        newWindow.setTitle("поиск");
        newWindow.setScene(scene);
        newWindow.show();


    }

}

