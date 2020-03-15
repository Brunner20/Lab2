package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Controller {
    private ObservableList<Tournament> tournaments;
    public  Controller(ObservableList<Tournament> list){
        tournaments=list;
    }

    public void load(String pathFile){
        Load load =new Load();
        this.tournaments=load.parse(pathFile);
        }
    public void save(String pathFile){
        Save save=new Save();
        save.parser(pathFile,tournaments);
    }
    public  ObservableList<Tournament> getTournaments(){
        return  this.tournaments;
    }
    public void setTournaments(ObservableList<Tournament> tournaments){
        this.tournaments=tournaments;
    }
    public void addTournament(Tournament tournament){
        tournaments.add(tournament);
    }
    public void deleteTournament(ObservableList<Tournament> tournament){
        for(Tournament tour:tournament)
        this.tournaments.remove(tour);
    }

    public ObservableList<Tournament> findByNameOfTournament(String nameOfTournament){
        ObservableList<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            if (tour.getNameOfTournament().equals(nameOfTournament))
                list.add(tour);
        }
        return list;
    }
    public ObservableList<Tournament> findByTypeOfSport(String type){
        ObservableList<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            if (tour.getTypeOfSport().equals(type))
                list.add(tour);
        }
        return list;
    }
    public ObservableList<Tournament> findByDateOfTournament(LocalDate date){
        ObservableList<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            if (tour.getDateOfTournament().equals(date))
                list.add(tour);
        }
        return list;
    }

    public ObservableList<Tournament> findByWinnerOfTournament(Person person){
        ObservableList<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            if (tour.getWinner().getFirstname().equals(person.getFirstname()))
                list.add(tour);
            else if(tour.getWinner().getLastname().equals(person.getLastname()))
                list.add(tour);
            else if (tour.getWinner().getMiddlename().equals(person.getMiddlename()))
                list.add(tour);

        }
        return list;
    }
    public ObservableList<Tournament> findByPrize(int lowerPrize,int upperPrize){
        ObservableList<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            if (tour.getPrize()<upperPrize && tour.getPrize()>lowerPrize)
                list.add(tour);

        }
        return list;
    }
    public ObservableList<Tournament> findByIncome(int lowerPrize,int upperPrize){
        ObservableList<Tournament> list= FXCollections.observableArrayList();
        for(Tournament tour: tournaments) {
            if (tour.getIncome()<upperPrize && tour.getIncome()>lowerPrize)
                list.add(tour);

        }
        return list;
    }
}
