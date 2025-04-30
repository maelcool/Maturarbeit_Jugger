import gui.StartGUI;
import javafx.application.Application;

import org.apache.logging.log4j.LogManager;


public class Main {
    //private static final Logger eLogger = LogManager.getLogger(Main.class); // Error Logger

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("InfoLogger");
    private final String[] argsForMainMethod = new String[0];
    public String[] getArgsForMainMethod(){
        return argsForMainMethod;
    }
    public static void main(String[] args) {
        System.setProperty("log4j2.debug", "true");

        Logger.info("Start in der Main Funktion");
        System.out.println("Hello world!");
        Application.launch(StartGUI.class);
    }
}