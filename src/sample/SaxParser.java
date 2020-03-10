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
import java.time.LocalDate;
import java.util.*;

public class SaxParser {

    private  ObservableList<Tournament> tournaments= FXCollections.observableArrayList();

    public   ObservableList<Tournament> parse(String pathFile)  {


        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        AdvancedXMLHandler handler = new AdvancedXMLHandler();
        try {
            parser.parse(new File(pathFile), handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for(Tournament tour: tournaments)
//            System.out.println(String.format("Имя соревнования: %s, вид спорта: %s, победитель: %s,награды: %d," +
//                            "заработок: %.1f, дата проведения %tD"
//                    , tour.getNameOfTournament(), tour.getTypeOfSport(),tour.getName(),tour.getPrize(),
//                    tour.getIncome(),tour.getDateOfTournament()));
        return  tournaments;

    }
    private  class AdvancedXMLHandler extends DefaultHandler {//было статик
        private String nameOfTournament, typeOfSport,name, lastElementName;
        private  int prize;
        private LocalDate dateOfTournament;

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
               dateOfTournament= LocalDate.of(Integer.parseInt(information.substring(0,4)),
                      Integer.parseInt(information.substring(5,7)),Integer.parseInt(information.substring(8,10)));
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

}
