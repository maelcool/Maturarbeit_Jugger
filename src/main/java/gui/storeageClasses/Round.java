package gui.storeageClasses;

import java.util.List;

public class Round {
    private int number;
    private boolean gruen;
    private List<Fight> fights;


    // Getters und Setters für Klasse:
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isGruen() {
        return gruen;
    }

    public void setGruen(boolean gruen) {
        this.gruen = gruen;
    }

    public List<Fight> getFights() {
        return fights;
    }

    public void setFights(List<Fight> fights) {
        this.fights = fights;
    }
}
