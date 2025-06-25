package gui.controller;

import gui.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class FileOeffnenController {
    public static File selectedFile = null;

    @FXML
    void newFile(ActionEvent event) throws Exception {
        FileHandler.createANewFile();
    }
    @FXML
    void openFIle(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null){
            FileHandler.openAFile(selectedFile);
        }
        System.out.println("Selected file: " + selectedFile);
    }
}
