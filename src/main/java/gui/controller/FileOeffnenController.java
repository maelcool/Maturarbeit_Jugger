package gui.controller;

import gui.Write;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class FileOeffnenController {

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
