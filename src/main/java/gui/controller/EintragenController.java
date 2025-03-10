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
    private static final String[] data = new String[3];
    private String embedUrl;

    public static String[] getData(){
        return data;
    }

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

        String videoId = "0pc9Uf3vFDU";
        embedUrl = "https://www.youtube.com/embed/u_BJ4ew1YNw"+ "?autoplay=1";
        webEngine.load(embedUrl);
    }

    public void zugSelected(){
        addValueToData(data, embedUrl);
        addValueToData(data, pompfen1.getValue());
        addValueToData(data, pompfen2.getValue());
        addValueToData(data, pompfen4.getValue());
        addValueToData(data, pompfen5.getValue());
        Write.writeToFile(data, "");
    }
    private void addValueToData(int index, String value) {
        data[index] = (value != null ? value : "null");
    }

}
