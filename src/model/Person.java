package model;

public class Person {
   private String firstname,lastname,middlename;


    public Person (String last,String first, String middle){
        this.firstname=first;
        this.lastname=last;
        this.middlename=middle;
    }
    public Person (){
        lastname=null;
        firstname=null;
        middlename=null;
    }
    public  void setFirstName(String firstname){this.firstname=firstname;}
    public  String getFirstName(){return  this.firstname;}
    public  void setLastName(String lastname){this.lastname=lastname;}
    public  String getLastName(){return  this.lastname;}
    public  void setMiddleName(String middlename){this.middlename=middlename;}
    public  String getMiddleName(){return  this.middlename;}
}

