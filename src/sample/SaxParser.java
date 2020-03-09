package sample;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.io.IOException;
import java.io.File;
import java.util.*;

public class SaxParser {

    private static ObservableList<Tournament> tournaments= FXCollections.observableArrayList();

    public SaxParser(String pathFile) throws ParserConfigurationException, SAXException, IOException {


        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        AdvancedXMLHandler handler = new AdvancedXMLHandler();
        parser.parse(new File(pathFile), handler);

        for(Tournament tour: tournaments)
            System.out.println(String.format("Имя соревнования: %s, вид спорта: %s, победитель: %s,награды: %d," +
                            "заработок: %.1f, дата проведения %tD"
                    , tour.getNameOfTournament(), tour.getTypeOfSport(),tour.getName(),tour.getPrize(),
                    tour.getIncome(),tour.getDateOfTournament()));


    }
    private static class AdvancedXMLHandler extends DefaultHandler {
        private String nameOfTournament, typeOfSport,name, lastElementName;
        private  int prize;
        private Calendar dateOfTournament;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("NameOfTournament"))
                    nameOfTournament = information;
                if (lastElementName.equals("TypeOfSport"))
                    typeOfSport = information;
                if(lastElementName.equals("name"))
                    name=information;
                if(lastElementName.equals("DateOfTournament"))
                    dateOfTournament= new GregorianCalendar(Integer.parseInt(information.substring(6)),
                            Integer.parseInt(information.substring(0,2)),Integer.parseInt(information.substring(0,2)));
                if(lastElementName.equals("prize"))
                    prize=Integer.parseInt(information);

            }
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ( (name != null && !name.isEmpty()) && (nameOfTournament != null && !nameOfTournament.isEmpty())&&
                    (typeOfSport !=null&& !typeOfSport.isEmpty()) && (dateOfTournament !=null  && prize !=0)){

                tournaments.add(new Tournament(nameOfTournament,typeOfSport,name,dateOfTournament,prize));
                nameOfTournament = null;
                typeOfSport = null;
                name=null;
                dateOfTournament=null;
                prize=0;
            }
        }
    }
    public static ObservableList<Tournament> getList(){
          return tournaments;}
}
