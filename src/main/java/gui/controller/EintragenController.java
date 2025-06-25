package gui.controller;


import com.sun.tools.javac.Main;
import gui.StartGUI;
import gui.fileWriting.JsonFileReader;
import gui.storeageClasses.Game;
import gui.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.sun.tools.javac.Main.*;

public class EintragenController{
    private static EintragenController instance;
    public static EintragenController getInstance() {
        return instance;
    }

    //FXML Variablen
    @FXML
    private Button SearchYoutubeVideoButton;
    @FXML
    private ChoiceBox<Integer> zuegeAuswahl;
    @FXML
    private ChoiceBox<String> pompfen1;
    @FXML
    private ChoiceBox<String> pompfen2;
    @FXML
    private ChoiceBox<String> pompfen4;
    @FXML
    private ChoiceBox<String> pompfen5;
    @FXML
    private ChoiceBox<String> spieli1;
    @FXML
    private ChoiceBox<String> spieli2;
    @FXML
    private ChoiceBox<String> spieli3;
    @FXML
    private ChoiceBox<String> spieli4;
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

    @FXML
    public void initialize(){
        instance = this;
    }
    @FXML
    public void readFile() {
        Game game = Game.getInstance();
        pompfen1.setItems(FXCollections.observableArrayList(choices));
        pompfen2.setItems(FXCollections.observableArrayList(choices));
        pompfen4.setItems(FXCollections.observableArrayList(choices));
        pompfen5.setItems(FXCollections.observableArrayList(choices));
        try {
            JsonFileReader.readJsonFromFile(FileOeffnenController.selectedFile, game);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {

            youtubeURL = game.getYoutubeLink();
            anzahlZuege = game.getHowManyRounds();
            eigenesTeam = game.getOwnTeam();
            gegenerischesTeam = game.getEnemyTeam();
            turnier = game.getTournament();
            System.out.println("Players: ");
            spielerInnen = game.getPlayers().toArray(new String[0]);
            System.out.println("After Players: ");
            spielerInnenOptionen = FXCollections.observableArrayList(spielerInnen);


            spieli1.setItems(FXCollections.observableArrayList(spielerInnenOptionen));
            spieli2.setItems(FXCollections.observableArrayList(spielerInnenOptionen));
            spieli3.setItems(FXCollections.observableArrayList(spielerInnenOptionen));
            spieli4.setItems(FXCollections.observableArrayList(spielerInnenOptionen));
            zuegeAuswahl.setOnAction(event -> {
                Integer selected = zuegeAuswahl.getValue();
                if (selected != null) {
                    zugSelected();
                }
            });
            StartGUI.getCurrentStage().setTitle("Jugger: " + eigenesTeam +" vs " + gegenerischesTeam + " in " + turnier + ".");
        } catch (Exception e) {
            System.out.println("ERROR: EintragenController, " + e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("File kann nicht gelesen werden");
            alert.showAndWait();
        }
    }
    public
    @FXML
    void SearchYoutubeVideoButtonClicked(ActionEvent event) {
        WebEngine webEngine = webView.getEngine();

        String youtubeUrl = "https://www.youtube.com/watch?v=_UtgZjul_Mc&t"; // Replace with actual video ID

        // Append parameters to request the highest quality available
        youtubeUrl += "&vq=hd1080"; // You can use "hd1080" or "highres" to request higher quality

        webEngine.load(youtubeUrl);
    }

    public void zugSelected(){
        addValueToData(15, pompfen1.getValue());
        addValueToData(16, pompfen2.getValue());
        addValueToData(17, pompfen4.getValue());
        addValueToData(18, pompfen5.getValue());
        FileHandler.writeToFile();
    }
    private void addValueToData(int index, String value) {
        AngabenController.setData(value != null ? value : "null", index);
    }
    private void addValueToZuege(int index, String value) {
        zuege.add(value != null ? value : "null");
    }

}
