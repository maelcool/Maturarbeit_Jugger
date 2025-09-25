package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import gui.controller.AngabenController;
import gui.controller.EintragenController;
import gui.controller.FileOeffnenController;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;


public class FileHandler {
    private static File file;
    public static String dataString;
    public static ArrayList<String> dataArrayList;


    public static void writeToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                System.out.println(AngabenController.getData().length);
                for (String line : AngabenController.getData()) {
                    System.out.println("ASDDDD");
                    if(line !=null) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                System.out.println("File written successfully.");
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }
    }
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
            dataString = FileUtils.readFileToString(selectedFile);
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
