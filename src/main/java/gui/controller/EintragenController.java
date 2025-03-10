package gui.controller;

import gui.Write;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EintragenController {
    //FXML Variablen
    @FXML
    private Button SearchYoutubeVideoButton;
    @FXML
    private ChoiceBox<String> pompfen1;
    @FXML
    private ChoiceBox<String> pompfen2;
    @FXML
    private ChoiceBox<String> pompfen4;
    @FXML
    private ChoiceBox<String> pompfen5;
    @FXML
    private WebView webView;

    //private Variablen
    public static ObservableList<String> choices = FXCollections.observableArrayList("Stab", "Kette", "Langpompfe", "Q-Tipp", "Schild", "DPK", "Einzel-kurzpopfe");
    private static final ArrayList<String> zuege = new ArrayList<String>();
    private String embedUrl;

    @FXML
    public void initialize() {
        pompfen1.setItems(FXCollections.observableArrayList(choices));
        pompfen2.setItems(FXCollections.observableArrayList(choices));
        pompfen4.setItems(FXCollections.observableArrayList(choices));
        pompfen5.setItems(FXCollections.observableArrayList(choices));

        pompfen1.setOnAction(event -> {
            String selected = pompfen1.getValue();
            if (selected != null) {
                zugSelected();
            }
        });
    }
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
        Write.writeToFile();
    }
    private void addValueToData(int index, String value) {
        data[index] = (value != null ? value : "null");
    }
    private void addValueToZuege(int index, String value) {
        zuege.add(value != null ? value : "null");
    }

}
