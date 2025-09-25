package gui;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gui.controller.FileOeffnenController;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;


public class FileHandler {
    private static File file; //It is used
    public static String dataString;
    public static ArrayList<String> dataArrayList;

    /**
    * Opens a FileChooser so the user can create a new .jugger file.
    * If the file doesn’t exist, it creates it and sets it as the current file.
    * Then opens the next scene in the GUI.
    * @throws Exception if creating the file or opening the scene fails
    */
    public static void createANewFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Jugger Files", "*.jugger"));
        File selectedFile = fileChooser.showSaveDialog(StartGUI.getCurrentStage());
    

        if (selectedFile != null) {
            if (!selectedFile.exists()) {
                try {
                    boolean fileCreated = selectedFile.createNewFile();
                    if (fileCreated){
                        file = selectedFile;
                        FileOeffnenController.selectedFile = selectedFile;
                        openNewScene();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Error: An error occurred while creating the file.");
                }
            } else {
                System.out.println("File Exists The file already exists: " + selectedFile.getAbsolutePath());
            }
        }
    }
    public static void openAFile(File selectedFile) throws Exception {
        file = selectedFile;
        if (!FilenameUtils.getExtension(selectedFile.getName()).equals(".jugger")){
            dataString = FileUtils.readFileToString(selectedFile, StandardCharsets.UTF_8);
            Stream<String> linesFromString = dataString.lines();
            dataArrayList = (ArrayList<String>) linesFromString.collect(Collectors.toList());
            System.out.println(dataArrayList);
            openNewScene();
        }
    }

    private static void openNewScene() throws Exception {
        Stage newStage = new Stage();
        StartGUI.getInstance().switchToSecondScene(newStage);
    }
}
