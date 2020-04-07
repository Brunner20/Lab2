package controller;

import javafx.collections.FXCollections;
import model.Person;
import model.Tournament;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class Controller {

    private List<Tournament> tournaments;
    private PageOfTable page;
    public  Controller(List<Tournament> list){
        tournaments=list;
        page =new PageOfTable(tournaments);
    }

    public List<Tournament> load(File file){
        Load load =new Load();
        this.tournaments=load.parse(file);
        page =new PageOfTable(tournaments);
        return this.tournaments;
        }
    public void save(File file){
        Save save=new Save();
        save.parser(file,tournaments);
    }

    public void addTournament(Tournament tournament){
        tournaments.add(tournament);
    }
    public void deleteTournament( List<Tournament> tournament){
        for(Tournament tour:tournament)
        this.tournaments.remove(tour);
    }
    public  int getSize(){
        return  this.tournaments.size();
    }
    public List<String> getListTypeOfSport(){
        List<String> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
                list.add(tour.getTypeOfSport());
        }
        return list;
    }
    public List<Tournament> findByNameOfTournament(String nameOfTournament){
        List<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            if (tour.getNameOfTournament().equals(nameOfTournament))
                list.add(tour);
        }
        return list;
    }
    public List<Tournament> findByTypeOfSport(String type){
        List<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            System.out.println(tour.getTypeOfSport());
            if (tour.getTypeOfSport().equals(type))
                list.add(tour);
        }
        System.out.println(list.size());
        return list;
    }
    public List<Tournament> findByDateOfTournament(LocalDate date){
        List<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            if (tour.getDateOfTournament().equals(date))
                list.add(tour);
        }
        return list;
    }

    public List<Tournament> findByWinnerOfTournament(Person person){
        List<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            if (tour.getWinner().getFirstName().equals(person.getFirstName()))
                list.add(tour);
            else if(tour.getWinner().getLastName().equals(person.getLastName()))
                list.add(tour);
            else if (tour.getWinner().getMiddleName().equals(person.getMiddleName()))
                list.add(tour);

        }
        return list;
    }
    public List<Tournament> findByPrize(int lowerPrize,int upperPrize){
        List<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            if (tour.getPrize()<upperPrize && tour.getPrize()>lowerPrize)
                list.add(tour);

        }
        return list;
    }
    public List<Tournament> findByIncome(int lowerPrize,int upperPrize){
        List<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            if (tour.getIncome()<upperPrize && tour.getIncome()>lowerPrize)
                list.add(tour);

        }
        return list;
    }
    public  List<Tournament> getPage(int numberPage,int rowsInPage){
        return page.getPageOfTable(numberPage,rowsInPage);
    }
   public   int getLastPage(int rowsPerPage){
        return page.calculateLastPage(rowsPerPage);
    }
}
