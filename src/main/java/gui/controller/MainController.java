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
        tabsAreSwitched("Angaben");
    }
    public void eintragenAusgewaehlt(){
        tabsAreSwitched("Eintragen");
    }
    public void auswertungAusgewaehlt(){
        tabsAreSwitched("Auswertung");
    }
    public void tabsAreSwitched(String name){
        if (name.equals("Angaben")){
            System.out.println("ANGABEN  "  + name);
        }

        else if (name.equals("Eintragen")){
            System.out.println("EINTRAGEN "  + name);
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
        else if(name.equals("Auswertung")){
            System.out.print("SWITCHED" + Game.getInstance().rounds);
            AuswertungController.getInstance().readFile();
            System.out.print("SWITCHED" + Game.getInstance().rounds);
        }


        else {
            System.out.println("NAHH "  + name);
        }
    }

    public void setTab(Tab tab){
        tabPane.getSelectionModel().clearSelection();
        tabPane.getSelectionModel().select(tab);
    }

}
