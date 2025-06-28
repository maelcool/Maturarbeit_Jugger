package gui.storeageClasses;

import java.util.ArrayList;

public class Game {
    public String youtubeLink;
    public String ownTeam;
    public String enemyTeam;
    public String tournament;
    public ArrayList<String> players;
    public int howManyRounds;
    public ArrayList<Round> rounds;

    public static Game getInstance() {
        return new Game();
    }


    
}
