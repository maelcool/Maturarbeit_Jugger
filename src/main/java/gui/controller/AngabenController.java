package gui.controller;

import com.sun.tools.javac.Main;
import gui.StartGUI;
import gui.Write;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;


public class AngabenController {
    private static AngabenController instance;
    public static AngabenController getInstance() {
        return instance;
    }

    @FXML
    private Button SearchYoutubeVideoButton;
    @FXML
    private WebView webView; // This is the WebView injected from FXML
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
    private ListView listView;

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

        String videoId = "0pc9Uf3vFDU";
        String embedUrl = "https://www.youtube.com/embed/u_BJ4ew1YNw"+ "?autoplay=1";
        webEngine.load(embedUrl);
    }

    public void writeToTheDataArray(){
        dataArray[0] = youtubeURLField.getText();
        dataArray[1] = zuege.getText();
        dataArray[2] = eigenesTeam.getText();
        dataArray[3] = gegenerischesTeam.getText();
        dataArray[4] = turnier.getText();
        dataArray[5] = String.join("%&+", teamMembers);
        dataArray[6] = null;
        Write.writeToFile();
    }

    @FXML
    private void handleKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String name = teamPlayers.getText();
            teamMembers.add(name);
            listView.getItems().add(name);
            teamPlayers.clear();
        }
    }

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
