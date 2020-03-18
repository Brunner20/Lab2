package sample;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;
import java.time.LocalDate;

public class Load {

        public ObservableList<Tournament> parse(String pathFile) {

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            try {
                SAXParser saxParser = saxParserFactory.newSAXParser();
                LoaderHandler handler = new LoaderHandler();
                saxParser.parse(new File(pathFile), handler);
                 return handler.getTournaments();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

    private static class LoaderHandler extends DefaultHandler {
        private boolean bnameOfTournament = false;
        private boolean btypeOfSport = false;
        private boolean bdateOfTournament = false;
        private boolean blastName = false;
        private boolean bfirstName = false;
        private boolean bmiddleName = false;
        private boolean bprize = false;

        private  ObservableList<Tournament> tournaments = null;
        private Tournament tournament = null;
        private  Person person=null;
        private String data;

        public ObservableList<Tournament> getTournaments() {
            if(!tournaments.isEmpty())
                for(Tournament tour: tournaments)
                    System.out.println(String.format("Имя соревнования: %s, вид спорта: %s, победитель n: %s, победитель f: %s, победитель m: %s,награды: %d," +
                                    "заработок: %.1f, дата проведения %tD"
                            , tour.getNameOfTournament(), tour.getTypeOfSport(),tour.getWinner().getFirstName(),
                            tour.getWinner().getLastName(),tour.getWinner().getMiddleName(),tour.getPrize(),
                            tour.getIncome(),tour.getDateOfTournament()));
            else System.out.println("empty");
            return tournaments;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if(qName.equalsIgnoreCase("tournament")) {
                tournament = new Tournament();
                person=new Person();
                if(tournaments == null) {
                     tournaments=FXCollections.observableArrayList();
                }
            }

            else if (qName.equalsIgnoreCase("nameOfTournament")) {

                bnameOfTournament = true;
            }
            else if (qName.equalsIgnoreCase("typeOfSport")) {
                btypeOfSport = true;
            }
            else if (qName.equalsIgnoreCase("dateOfTournament")) {
                bdateOfTournament = true;
            }
            else if (qName.equalsIgnoreCase("lastName")) {
                blastName = true;
            }
            else if (qName.equalsIgnoreCase("firstName")) {
                bfirstName = true;
            }
            else if (qName.equalsIgnoreCase("middleName")) {
                bmiddleName = true;
            }
            else if (qName.equalsIgnoreCase("prize")) {
                bprize = true;
            }

            data = "";

        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if(bnameOfTournament) {
                tournament.setNameOfTournament(data);
                bnameOfTournament = false;
            }
            else if (btypeOfSport) {
                tournament.setTypeOfSport(data);
                btypeOfSport = false;
            }
            else if (bdateOfTournament) {
                tournament.setDateOfTournament(LocalDate.of(Integer.parseInt(data.substring(0,4)),
                        Integer.parseInt(data.substring(5,7)),Integer.parseInt(data.substring(8,10))));
                bdateOfTournament = false;
            }
            else if (blastName) {
                person.setLastName(data);
                blastName = false;
            }
            else if (bfirstName) {
                person.setFirstName(data);
                bfirstName = false;
            }
            else if (bmiddleName) {
                person.setMiddleName(data);
                bmiddleName = false;
            }
            else if (bprize) {

                tournament.setPrize(Integer.parseInt(data));
                bprize = false;
            }

            if (qName.equalsIgnoreCase("tournament")) {

                tournament.setWinner(person);
                tournaments.add(tournament);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            data=new String(ch, start, length);

        }

    }




}


