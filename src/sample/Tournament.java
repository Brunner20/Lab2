package sample;

import java.time.LocalDate;


public class Tournament {
    private String nameOfTournament;
    private String typeOfSport;
    private LocalDate dateOfTournament;
    private Person winner;
    private int prize;
    private double income;

public Tournament(String nameOfTournament, String typeOfSport, Person winner, LocalDate dateOfTournament, int prize){
    this.nameOfTournament =nameOfTournament;
    this.typeOfSport =typeOfSport;
    this.dateOfTournament =dateOfTournament;
    this.winner =winner;
    this.prize =prize;
    this.income =0.6*prize;
}
    public Tournament(){
        this.nameOfTournament =null;
        this.typeOfSport =null;
        this.dateOfTournament =null;
        this.winner =null;
        this.prize =0;
        this.income =0;
    }
public String getNameOfTournament(){ return nameOfTournament;}
public void setNameOfTournament(String name){this.nameOfTournament =name;}
public  String getTypeOfSport(){return typeOfSport;}
public  void setTypeOfSport(String type){ this.typeOfSport =type;}
public  Person getWinner(){return winner;}
public  void  setWinner(Person winner){this.winner =winner;}
public LocalDate getDateOfTournament(){return dateOfTournament;}
public  void  setDateOfTournament(LocalDate cal){this.dateOfTournament =cal;}
public int getPrize(){ return prize;}
public void  setPrize(int prize){this.prize =prize; this.income=0.6*prize;}
public  double getIncome(){return income;}
}
