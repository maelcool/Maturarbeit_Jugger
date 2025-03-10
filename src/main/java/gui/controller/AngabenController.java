package gui.controller;

import com.sun.tools.javac.Main;
import gui.StartGUI;
import gui.Write;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private static final String[] data = new String[7];
    public static String[] getData() {
        return data;
    }

    @FXML
    void SearchYoutubeVideoButtonClicked(ActionEvent event) {
        WebEngine webEngine = webView.getEngine();

        String videoId = "0pc9Uf3vFDU";
        String embedUrl = "https://www.youtube.com/embed/u_BJ4ew1YNw"+ "?autoplay=1";
        webEngine.load(embedUrl);
    }
    @FXML
    void newFile(ActionEvent event) throws Exception {
       Write.createANewFile();
    }
    @FXML
    void openFIle(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null){
            Write.openAFile(selectedFile);
        }
    }

    private void writeToTheDataArray(){
        data[0] = youtubeURLField.getText();
        data[1] = zuege.getText();
        data[2] = eigenesTeam.getText();
        data[3] = gegenerischesTeam.getText();
        data[4] = turnier.getText();
        data[5] = String.join("%&%", teamMembers);
        data[6] = null;
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
}
