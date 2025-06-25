package gui.storeageClasses;

import java.util.ArrayList;

public class Game {
    private String youtubeLink;
    private String ownTeam;
    private String enemyTeam;
    private String tournament;
    private ArrayList<String> players;
    private int howManyRounds;
    private ArrayList<Round> rounds;

    public static Game getInstance() {
        return new Game();
    }


    // Getters und Setters für die Klasse:
    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getOwnTeam() {
        return ownTeam;
    }

    public void setOwnTeam(String ownTeam) {
        this.ownTeam = ownTeam;
    }

    public String getEnemyTeam() {
        return enemyTeam;
    }

    public void setEnemyTeam(String enemyTeam) {
        this.enemyTeam = enemyTeam;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public int getHowManyRounds() {
        return howManyRounds;
    }
    public void setHowManyRounds(int howManyRounds) {
        this.howManyRounds = howManyRounds;
    }
}
