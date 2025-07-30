package gui.controller;

import java.io.IOException;
import java.util.ArrayList;

import gui.fileWriting.JsonFileReader;
import gui.storeageClasses.Game;
import gui.storeageClasses.Round;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class AuswertungController {

    @FXML
    TabPane tabPane;

    Game game = Game.getInstance();

    private ArrayList<String> spielerInnen;
    private ArrayList<Round> rounds;
    private static AuswertungController instance;
    public static AuswertungController getInstance() {
        return instance;
    }

    @FXML
    public void initialize(){
        instance = this;
    }
    
    @FXML
    public void readFile() {

        System.out.print("game");
        try {
            JsonFileReader.readJsonFromFile(FileOeffnenController.selectedFile, game);
            spielerInnen = game.players;
            rounds = game.rounds;
            } 
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        for (String name : spielerInnen){
            Tab tab = createANewTab(name);
            tabPane.getTabs().add(tab);
        }
    }
    


    private Tab createANewTab (String name){

        System.out.print("Create A New Tab Called");
        AnchorPane anchorPane = new AnchorPane();
        GridPane gridPane = new GridPane();
        TableView<?> tableView = new TableView<>();
        gridPane.add(tableView, 1, 1);
        AnchorPane.setTopAnchor(gridPane, 0.0);
        AnchorPane.setBottomAnchor(gridPane, 0.0);
        AnchorPane.setLeftAnchor(gridPane, 0.0);
        AnchorPane.setRightAnchor(gridPane, 0.0);

        anchorPane.getChildren().add(gridPane);

        Tab tab = new Tab(name);
        tab.setContent(anchorPane);
        return tab;
    }
}
