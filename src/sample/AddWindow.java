package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class AddWindow {
    public static void show(){
        Label tourNameLa=new Label("название турнира");
        TextField tourName=new TextField();
        Label tourTypeLa=new Label("вид спорта");
        TextField tourType=new TextField("");
        Label tourDateLa=new Label("дата проведения");
        DatePicker tourDate = new DatePicker();
        Label tourWinnerLa=new Label("победитель турнира");
        TextField tourWinner=new TextField();
        Label tourPrizeLa=new Label("призовые");
        TextField tourPrize=new TextField();
        Button addBtn=new Button("добавить");

        ObservableList<Tournament> findList = FXCollections.observableArrayList();


        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Tournament findSample=new Tournament(tourName.getText(),tourType.getText(), tourWinner.getText(),
                        tourDate.getValue(),Integer.parseInt(tourPrize.getText()));

                SaxParser forBox=new SaxParser();
                ObservableList<Tournament> list= forBox.parse("src/sample/work2.xml");
                list.add(findSample);
                DomParser dom=new DomParser();
                dom.parser("src/sample/work2.xml",list);

            }
        });

        FlowPane paneN=new FlowPane(Orientation.HORIZONTAL, 20, 10);
        paneN.getChildren().addAll(tourNameLa,tourName);
        FlowPane paneD=new FlowPane(Orientation.HORIZONTAL, 25, 10);
        paneD.getChildren().addAll(tourDateLa,tourDate);
        FlowPane paneT=new FlowPane(Orientation.HORIZONTAL, 58, 10);
        paneT.getChildren().addAll(tourTypeLa,tourType);
        FlowPane paneW=new FlowPane(Orientation.HORIZONTAL, 7, 10);
        paneW.getChildren().addAll(tourWinnerLa,tourWinner);
        FlowPane paneP=new FlowPane(Orientation.HORIZONTAL, 65, 10);
        paneP.getChildren().addAll(tourPrizeLa,tourPrize);


        FlowPane pane1=new FlowPane(Orientation.VERTICAL, 10, 10);
        pane1.getChildren().addAll(paneN,paneD,paneT,paneW,paneP,addBtn);
        pane1.setPadding(new Insets(20));
        Scene scene = new Scene(pane1,800,300);
        Stage newWindow = new Stage();
        newWindow.setTitle("поиск");
        newWindow.setScene(scene);
        newWindow.show();
    }
}
