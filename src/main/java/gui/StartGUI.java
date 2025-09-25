package gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        stage.setTitle("Jugger - File Öffnen");
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
        stage.setTitle("Jugger");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.show();
    }
}
