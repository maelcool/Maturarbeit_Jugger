package gui.controller;

import gui.storeageClasses.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController {
    @FXML
    public TabPane tabPane;
    @FXML
    public Tab angaben;
    @FXML
    public Tab eintragen;
    @FXML
    public Tab auswertung;
    public void angabenAusgewaehlt(){
        System.out.println("ANGABEN  ");
    }
    public void eintragenAusgewaehlt(){
            if(AngabenController.getInstance().checkAllAnsweresAreGiven()){
                try {
                    AngabenController.getInstance().writeToGameAndToFile();
                    System.out.println("ALL ANSWERS ARE GIVEN");
                    EintragenController.getInstance().readFile();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("EVERYTHING SELECTED");
            }else {
                System.out.println("NOT EVERYTHING SELECTED");
                setTab(angaben);
            };
    }
    public void auswertungAusgewaehlt(){
        System.out.print("SWITCHED" + Game.getInstance().rounds);
            AuswertungController.getInstance().readFile();
            System.out.print("SWITCHED" + Game.getInstance().rounds);
    }

    public void setTab(Tab tab){
        tabPane.getSelectionModel().clearSelection();
        tabPane.getSelectionModel().select(tab);
    }

}
