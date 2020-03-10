package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class FindRows {
    public static ObservableList<Tournament> find(String path,Tournament sample)  {
        SaxParser saxForMain= new SaxParser();
        ObservableList<Tournament> list=saxForMain.parse(path);
        ObservableList<Tournament> listForTable= FXCollections.observableArrayList();
        for(Tournament tour: list) {
        if(tour.getNameOfTournament().equals(sample.getNameOfTournament()))
            listForTable.add(tour);
        else if(tour.getDateOfTournament().equals(sample.getDateOfTournament()))
            listForTable.add(tour);
        else if(tour.getTypeOfSport().equals(sample.getTypeOfSport()))
            listForTable.add(tour);
        else if(tour.getName().equals(sample.getName()))
            listForTable.add(tour);
        else if(tour.getPrize()==sample.getPrize())
            listForTable.add(tour);
        else if(tour.getIncome()==sample.getIncome())
            listForTable.add(tour);
        }
        return listForTable;
        }
    }

