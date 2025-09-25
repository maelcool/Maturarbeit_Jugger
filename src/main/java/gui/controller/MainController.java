package gui.controller;

import gui.storeageClasses.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import main.Main;

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

    /**
    * Called when the user switches to the"Eintragen" tab.
    * First checks if all required fields in "Angaben" are filled.
    * If yes, writes the data to the Game and file, then reads the UI for Eintragen tab.
    * If not, goes back to the "Angaben" tab.
    */
    public void eintragenAusgewaehlt(){
            if(AngabenController.getInstance().checkAllAnsweresAreGiven()){
                try {
                    AngabenController.getInstance().writeToGameAndToFile();
                    System.out.println("ALL ANSWERS ARE GIVEN");
                    EintragenController.getInstance().readFile();
                } catch (Exception e) {
                    Main.Logger.error(e);
                    e.printStackTrace();
                }
                System.out.println("EVERYTHING SELECTED");
            }else {
                System.out.println("NOT EVERYTHING SELECTED");
                setTab(angaben);
            };
    }

    /**
    * Called when the "Auswertung" tab is selected.
    * Reads the Game data and sets up the player stats and tabs.
    */
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
