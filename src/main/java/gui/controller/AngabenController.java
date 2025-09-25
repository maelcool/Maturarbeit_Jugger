package gui.controller;

import gui.fileWriting.JsonFileWriter;
import gui.storeageClasses.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.util.ArrayList;


public class AngabenController {
    private static AngabenController instance;
    public static AngabenController getInstance() {
        return instance;
    }

    @FXML
    private Button SearchYoutubeVideoButton;
    @FXML
    private WebView webView;
    @FXML
    private TextField youtubeURLField;
    @FXML
    private TextField zuege;
    @FXML
    private TextField eigenesTeam;
    @FXML
    private TextField gegenerischesTeam;
    @FXML
    private TextField turnier;
    @FXML
    private TextField teamPlayers;
    @FXML
    private ListView<String> listView;

    private ArrayList<String> teamMembers = new ArrayList<String>();
    private static final String[] dataArray = new String[7];
    public static String[] getData() {
        return dataArray;
    }
    public static void setData(String value, int index){
        dataArray[index] = value;
    }

    @FXML
    public void initialize(){
        instance = this;
    }
    @FXML
    void SearchYoutubeVideoButtonClicked(ActionEvent event) {
        WebEngine webEngine = webView.getEngine();
        String videoId = youtubeURLField.getText();
        webEngine.load(videoId);
    }


    /**
     * Gets all Text from UI-Elements and stores them into Game.java.
     * Then writes the whole Game to the File.
     * @throws Exception
     */
    public void writeToGameAndToFile() throws Exception{
        System.out.println("writeToGameAndToFile called");
        Game game = Game.getInstance();
        game.youtubeLink = youtubeURLField.getText();
        game.howManyRounds = Integer.parseInt(zuege.getText());
        game.ownTeam = eigenesTeam.getText();
        game.enemyTeam = gegenerischesTeam.getText();
        game.tournament = turnier.getText();
        game.players = teamMembers;
        JsonFileWriter.writeTheGameToFile(game);
    }

    /**
     * If Enter is presed, the text from the Players Textfield is entered as a teammember.
     * @param event
     */
    @FXML
    private void handleKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String name = teamPlayers.getText();
            teamMembers.add(name);
            listView.getItems().add(name);
            teamPlayers.clear();
        }
    }

    /**
     * Checks if there is a link, Zuege, ownTeam, enemyTeam, tournemnet and some player names.
     * If not, an Error Alert is shown and the Tab can't be switched.
     * @return true or false
     */
    public boolean checkAllAnsweresAreGiven(){
        boolean allAnswersGiven = true;
        if(youtubeURLField.getText().isEmpty()){
            youtubeURLField.setPromptText("Das ist ein Pflichtfeld");
            allAnswersGiven = false;
            youtubeURLField.setStyle("-fx-background-color: #ff7373;");
            System.out.println("MISSING YOUTUBEURL");
        }
        if(zuege.getText().isEmpty()){
            zuege.setPromptText("Das ist ein Pflichtfeld");
            allAnswersGiven = false;
            System.out.println("MISSING ZUEGE");
        }
        if(eigenesTeam.getText().isEmpty()){
            eigenesTeam.setPromptText("Das ist ein Pflichtfeld");
            System.out.println("MISSING EIGENES TEAM");
            allAnswersGiven = false;
        }
        if(gegenerischesTeam.getText().isEmpty()){
            gegenerischesTeam.setPromptText("Das ist ein Pflichtfeld");
            System.out.println("MISSING GEGENER TEAM");
            allAnswersGiven = false;
        }
        if(turnier.getText().isEmpty()){
            turnier.setPromptText("Das ist ein Pflichtfeld");
            System.out.println("MISSING TURNIER");
            allAnswersGiven = false;
        }
        if(String.join("%&+", teamMembers).isEmpty()){
            System.out.println("MISSING TEAMMITGLIEDER");
            allAnswersGiven = false;
        }
        if(!allAnswersGiven){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Fields");
            alert.setHeaderText("Please fill in all the required fields.");
            alert.showAndWait();
        }
        System.out.println("Alle Antworten Ende: " + allAnswersGiven);
        System.out.println(turnier.getText() + gegenerischesTeam.getText());
        return allAnswersGiven;
    }
}
