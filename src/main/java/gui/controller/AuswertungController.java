package gui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gui.fileWriting.JsonFileReader;
import gui.storeageClasses.Fight;
import gui.storeageClasses.Game;
import gui.storeageClasses.Round;
import gui.storeageClasses.tableViewStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import main.Main;

public class AuswertungController {

    @FXML
    TabPane tabPane;
    @FXML
    Text gruenProzent;
    @FXML
    TableView<tableViewStorage> tableView;

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
        addColoumnsToTableViewPlayPercentageName(tableView);
        try {
            JsonFileReader.readJsonFromFile(FileOeffnenController.selectedFile, game);
            spielerInnen = game.players;
            rounds = game.rounds;
            System.out.print(rounds.toString());
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        for (String name : spielerInnen){
            Tab tab = createANewTab(name);
            tabPane.getTabs().add(tab);
        }
        tableViewStorage[] tableViewList = new tableViewStorage[game.players.size()];
        gruenProzent.setText("Prozent Grün: " + getGreen() * 100 + "%");
        setAllEntriesForFirstTableView(tableViewList);
    }

    //_______________________________________________________________________//


    
    private void setAllEntriesForFirstTableView(tableViewStorage[] tableViewList){
        int counter = 0;
        for(int i = 0; i < game.players.size(); i++){
            tableViewList[i] = new tableViewStorage();
        }
        for (tableViewStorage tableViewEntry : tableViewList){
            tableViewEntry.name = game.players.get(counter);
            double percentageNumber = 100 * (getRoundsForPlayer(tableViewEntry.name, game) / (double) game.howManyRounds);
            tableViewEntry.playPercentage  = String.format("%.2f%%", percentageNumber);
            tableView.getItems().add(tableViewEntry);
            counter++;
        }
    }

     private void setEntryForSinglePlayerTableView(TableView<tableViewStorage> tableView, String playerName) {
        tableViewStorage entry = new tableViewStorage();
        entry.name = playerName;
        double playPercentageNumber = 100 * (getRoundsForPlayer(playerName, game) / (double) game.howManyRounds);
        entry.playPercentage = String.format("%.2f%%", playPercentageNumber);
        double winPercentageNumber = 100 * getFightPercentageForPlayer(playerName, game); // already a ratio
        entry.winPercentage = String.format("%.2f%%", winPercentageNumber);

        tableView.getItems().add(entry);
    }



    //___________________________________________________//



    private void addColoumnsToTableViewPlayPercentageName(TableView<tableViewStorage> tableViewNew){
        TableColumn<tableViewStorage, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<tableViewStorage, String> playPercentCol = new TableColumn<>("Play Percentage");
        playPercentCol.setCellValueFactory(new PropertyValueFactory<>("playPercentage"));

        tableViewNew.getColumns().addAll(nameCol, playPercentCol);
    }



    private void addColoumnsToTableViewPlayAndWinPercentageName(TableView<tableViewStorage> tableViewNew){
        TableColumn<tableViewStorage, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<tableViewStorage, String> winPercentCol = new TableColumn<>("Win Percentage");
        winPercentCol.setCellValueFactory(new PropertyValueFactory<>("winPercentage"));

        TableColumn<tableViewStorage, String> playPercentCol = new TableColumn<>("Play Percentage");
        playPercentCol.setCellValueFactory(new PropertyValueFactory<>("playPercentage"));

        tableViewNew.getColumns().addAll(nameCol, winPercentCol, playPercentCol);
    }


    //______________________________________________________//

    private Tab createANewTab(String name){
        System.out.print("Create A New Tab Called");
        AnchorPane anchorPane = new AnchorPane();
        GridPane gridPane = new GridPane();
        
        TableView<tableViewStorage> tableViewOfTab = new TableView<>();
        addColoumnsToTableViewPlayAndWinPercentageName(tableViewOfTab);
        setEntryForSinglePlayerTableView(tableViewOfTab, name);

        gridPane.add(tableViewOfTab, 1, 1);
        AnchorPane.setTopAnchor(gridPane, 0.0);
        AnchorPane.setBottomAnchor(gridPane, 0.0);
        AnchorPane.setLeftAnchor(gridPane, 0.0);
        AnchorPane.setRightAnchor(gridPane, 0.0);

        anchorPane.getChildren().add(gridPane);

        Tab tab = new Tab(name);
        tab.setContent(anchorPane);
        return tab;
    }







    //__________________________________________________________________//
    private double getGreen(){
        if (game.rounds.size() < game.howManyRounds) {
            Main.Logger.warn("roundList is empty in getGreen()");
            return 100000;
        }

        int greenCounter= 0;
        for (int i = 0; i <game.howManyRounds; i++){
            if (game.rounds.get(i).gruen){
                greenCounter++;
            }
        }
        Main.Logger.info("WIviel Grün: " + greenCounter);
        double percentOfGreen = (double) greenCounter / game.howManyRounds;
        return percentOfGreen;
    }

    private int getRoundsForPlayer(String playerName, Game game) {
        int result = 0;

        for (Round round : game.rounds) {
            for (Fight fight : round.fights) {
                if (fight.name != null && fight.name.equals(playerName)) {
                    Main.Logger.info("Player " + playerName + " participated in round " + round.numberOfRound);
                    result++;
                    break; // one fight is enough to confirm participation in the round
                }
            }
        }

        return result;
    }
    private double getFightPercentageForPlayer(String playerName, Game game) {
        int allFights = 0;
        int howManyWins = 0;
        for (Round round : game.rounds) {
            for (Fight fight : round.fights) {
                if (fight.name != null && fight.name.equals(playerName)) {
                    Main.Logger.info("Player " + playerName + " participated in round " + round.numberOfRound);
                    if(fight.gewonnen){
                        howManyWins++;
                    }
                    allFights++;
                    break; 
                }
            }
        }

        if (allFights == 0) return 0.0;  // Avoid division by zero

        return (double) howManyWins / allFights;
    }
}
