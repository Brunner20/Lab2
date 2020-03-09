package sample;

import java.util.Calendar;

public class Tournament {
    private String nameOfTournament;
    private String typeOfSport;
    private Calendar dateOfTournament;
    private String name;
    private int prize;
    private double income;
public Tournament(String NameOfTournament,String TypeOfSport,String Name,Calendar DateOfTournament,int prize){
    this.nameOfTournament =NameOfTournament;
    this.typeOfSport =TypeOfSport;
    this.dateOfTournament =DateOfTournament;
    this.name =Name;
    this.prize =prize;
    this.income =0.6*prize;

}
public String getNameOfTournament(){ return nameOfTournament;}
public void setNameOfTournament(String name){this.nameOfTournament =name;}
public  String getTypeOfSport(){return typeOfSport;}
public  void setTypeOfSport(String type){ this.typeOfSport =type;}
public  String getName(){return name;}
public  void  setName(String name){this.name =name;}
public Calendar getDateOfTournament(){return dateOfTournament;}
public  void  setDateOfTournament(Calendar cal){this.dateOfTournament =cal;}
public int getPrize(){return prize;}
public void  setPrize(int prize){this.prize =prize;}
public  double getIncome(){return income;}
public  void  setIncome(double inc){this.income =inc;}



}
