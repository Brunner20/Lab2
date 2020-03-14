package sample;

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
    public  void  setFirstname(String firstname){this.firstname=firstname;}
    public  String getFirstname(){return  this.firstname;}
    public  void  setLastname(String lastname){this.lastname=lastname;}
    public  String getLastname(){return  this.lastname;}
    public  void setMiddlename(String middlename){this.middlename=middlename;}
    public  String getMiddlename(){return  this.middlename;}
}

