package gui.controller;

import com.sun.tools.javac.Main;
import gui.StartGUI;
import gui.Write;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class AngabenController {

    @FXML
    private Button SearchYoutubeVideoButton;
    @FXML
    private WebView webView; // This is the WebView injected from FXML

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


}
