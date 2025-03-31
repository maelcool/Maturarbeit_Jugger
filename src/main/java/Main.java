import gui.StartGUI;
import javafx.application.Application;


public class Main {
    private final String[] argsForMainMethod = new String[0];
    public String[] getArgsForMainMethod(){
        return argsForMainMethod;
    }
    public static void main(String[] args) {

        System.out.println("Hello world!");
        Application.launch(StartGUI.class);
    }
}