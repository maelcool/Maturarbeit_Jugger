package gui;
import gui.controller.AngabenController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.util.Objects;

public class StartGUI extends Application{
    private static StartGUI instance;

    public static StartGUI getInstance() {
        return instance;
    }
    private static Stage currentStage;

    public static Stage getCurrentStage(){
        return currentStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        System.out.println(StartGUI.class.getResource("/fxml-Files/FileOeffnen.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(StartGUI.class.getResource("/fxml-Files/FileOeffnen.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Jugger - Angaben");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        currentStage = stage;
        stage.show();
    }
    public void switchToSecondScene(Stage stage) throws Exception {
        currentStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(StartGUI.class.getResource("/fxml-Files/Main.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene); // Switch to the new scene
        stage.setTitle("Jugger - Eintragen");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.show();
    }


}
