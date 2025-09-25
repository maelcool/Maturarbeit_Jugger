package gui.controller;

import gui.StartGUI;
import gui.fileWriting.JsonFileReader;
import gui.fileWriting.JsonFileWriter;
import gui.storeageClasses.Fight;
import gui.storeageClasses.Game;
import gui.storeageClasses.Round;
import gui.FileHandler;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import main.Main;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class EintragenController{
    private static EintragenController instance;
    public static EintragenController getInstance() {
        return instance;
    }

    //FXML Variablen
    @FXML
    private ChoiceBox<Integer> zuegeAuswahl;
    @FXML
    private CheckBox gruenCheckBox;
    @FXML
    private CheckBox gewonnen1;
    @FXML
    private CheckBox gewonnen2;
    @FXML
    private CheckBox gewonnen3;
    @FXML
    private CheckBox gewonnen4;
    @FXML
    private CheckBox gewonnen5;
    @FXML
    private CheckBox druckpunkt1;
    @FXML
    private CheckBox druckpunkt2;
    @FXML
    private CheckBox druckpunkt3;
    @FXML
    private CheckBox druckpunkt4;
    @FXML
    private CheckBox druckpunkt5;
    @FXML
    private ChoiceBox<String> pompfen1;
    @FXML
    private ChoiceBox<String> pompfen2;
    @FXML
    private ChoiceBox<String> pompfen4;
    @FXML
    private ChoiceBox<String> pompfen5;
     @FXML
    private ChoiceBox<String> gegenerPompfe1;
    @FXML
    private ChoiceBox<String> gegenerPompfe2;
    @FXML
    private ChoiceBox<String> gegenerPompfe3;
    @FXML
    private ChoiceBox<String> gegenerPompfe4;
    @FXML
    private ChoiceBox<String> spieli1;
    @FXML
    private ChoiceBox<String> spieli2;
    @FXML
    private ChoiceBox<String> spieli3;
    @FXML
    private ChoiceBox<String> spieli4;
    @FXML
    private ChoiceBox<String> lauefi;
    @FXML
    private WebView webView;

    //private Variablen
    private static ObservableList<String> choices = FXCollections.observableArrayList("Stab", "Kette", "Langpompfe", "Q-Tipp", "Schild", "DPK", "Einzel-kurzpopfe");

    private static final ArrayList<String> zuege = new ArrayList<String>();
    private static ArrayList<String> privateData;
    private String youtubeURL;
    private Integer anzahlZuege;
    private String eigenesTeam;
    private String gegenerischesTeam;
    private String turnier;
    private String[] spielerInnen;
    private ObservableList<String> spielerInnenOptionen;
    Game game = Game.getInstance();


    @FXML
    public void initialize(){
        instance = this;
    }
    @FXML
    public void readFile() {
       
        pompfen1.setItems(FXCollections.observableArrayList(choices));
        pompfen2.setItems(FXCollections.observableArrayList(choices));
        pompfen4.setItems(FXCollections.observableArrayList(choices));
        pompfen5.setItems(FXCollections.observableArrayList(choices));
        gegenerPompfe1.setItems(FXCollections.observableArrayList(choices));
        gegenerPompfe2.setItems(FXCollections.observableArrayList(choices));
        gegenerPompfe3.setItems(FXCollections.observableArrayList(choices));
        gegenerPompfe4.setItems(FXCollections.observableArrayList(choices));
        try {
            JsonFileReader.readJsonFromFile(FileOeffnenController.selectedFile, game);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {

            youtubeURL = game.youtubeLink;
            anzahlZuege = game.howManyRounds;
            eigenesTeam = game.ownTeam;
            gegenerischesTeam = game.enemyTeam;
            turnier = game.tournament;
            spielerInnen = game.players.toArray(new String[0]);
            spielerInnenOptionen = FXCollections.observableArrayList(spielerInnen);
            ObservableList<Integer> zuegeOptionenIntegers = FXCollections.observableArrayList(IntStream.rangeClosed(1, anzahlZuege).boxed().toList());
            zuegeAuswahl.setValue(0);

            spieli1.setItems(FXCollections.observableArrayList(spielerInnenOptionen));
            spieli2.setItems(FXCollections.observableArrayList(spielerInnenOptionen));
            spieli3.setItems(FXCollections.observableArrayList(spielerInnenOptionen));
            spieli4.setItems(FXCollections.observableArrayList(spielerInnenOptionen));
            lauefi.setItems(FXCollections.observableArrayList(spielerInnenOptionen));
            zuegeAuswahl.setItems(zuegeOptionenIntegers);
            zuegeAuswahl.setOnAction(event -> {
            Integer selected = zuegeAuswahl.getValue();
            if (selected != null && selected != 0) {  
                    zugSelected(selected);
                }
            });
            zuegeAuswahl.setValue(0);  

            try{
                WebEngine webEngine = webView.getEngine();
                youtubeURL += "&vq=highres"; // You can use "hd1080" or "highres" to request higher quality

                webEngine.load(youtubeURL);
            }catch(Error e){
                e.printStackTrace();
            }
            System.out.println("NOW SHOULD BE TITLE");
            StartGUI.getCurrentStage().setTitle("Jugger: " + eigenesTeam +" vs " + gegenerischesTeam + " in " + turnier + ".");
        } catch (Exception e) {
            System.out.println("ERROR: EintragenController, " + e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("File kann nicht gelesen werden");
            alert.showAndWait();
        }
    }

    public void zugSelected(int numberOfRound){
        for(Round round : game.rounds){
            if (round.numberOfRound == numberOfRound){
                Main.Logger.info("Round " + numberOfRound + " already exists. Skipping addition.");
                return;
            }
        }
        Round newRound = new Round();
        newRound.numberOfRound = numberOfRound;
        newRound.gruen = gruenCheckBox.isSelected();
        List<Fight> fights = new ArrayList<Fight>();
        fights.add(new Fight(1, spieli1.getValue(), gewonnen1.isSelected(), druckpunkt1.isSelected(), pompfen1.getValue() ,gegenerPompfe1.getValue()));
        fights.add(new Fight(2, spieli2.getValue(), gewonnen2.isSelected(), druckpunkt2.isSelected(), pompfen2.getValue() ,gegenerPompfe2.getValue()));
        fights.add(new Fight(3, lauefi.getValue()));
        fights.add(new Fight(4, spieli3.getValue(), gewonnen4.isSelected(), druckpunkt4.isSelected(), pompfen4.getValue() ,gegenerPompfe3.getValue()));
        fights.add(new Fight(5, spieli4.getValue(), gewonnen5.isSelected(), druckpunkt5.isSelected(), pompfen5.getValue() ,gegenerPompfe4.getValue()));
        System.out.print(fights);
        newRound.fights = fights;
        game.rounds.add(newRound);
        try {
            Main.Logger.info("Written to the Game" + game.rounds.toString());
            JsonFileWriter.writeTheGameToFile(game);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
