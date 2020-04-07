package controller;

import javafx.collections.FXCollections;
import model.Tournament;

import java.util.List;

public class PageOfTable {
    private List<Tournament> listOfTournament;

    PageOfTable(List<Tournament> list){
        listOfTournament=list;
    }

    List<Tournament> getPageOfTable(int pageNumber,int rowsPerPage){
        try {
            return listOfTournament.subList((pageNumber - 1) * rowsPerPage, pageNumber * rowsPerPage);
        }catch (Exception e){
            return listOfTournament.subList((pageNumber - 1) * rowsPerPage, listOfTournament.size());
        }
    }
      int calculateLastPage(int rowsPerPage){
        int lastPage;
        if(listOfTournament.size()%rowsPerPage!=0)
            lastPage=listOfTournament.size()/rowsPerPage+1;
        else lastPage=listOfTournament.size()/rowsPerPage;
        return lastPage;
    }
}
