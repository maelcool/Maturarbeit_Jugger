package gui.storeageClasses;

public class tableViewStorage {
    public String name;
    public String playPercentage;
    public String winPercentage;

    public tableViewStorage() {};

    public tableViewStorage(String name, String playPercentage) {
        this.name = name;
        this.playPercentage = playPercentage;
    };



    //public Getters and Setters for the Table View
    public String getName() {
        return name;
    }

    public String getPlayPercentage() {
        return playPercentage;
    }
    public String getWinPercentage() {
        return playPercentage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayPercentage(String playPercentage) {
        this.playPercentage = playPercentage;
    }
    public void setWinPercentage(String playPercentage) {
        this.playPercentage = playPercentage;
    }
}
