package gui.storeageClasses;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String youtubeLink;
    private String ownTeam;
    private String enemyTeam;
    private String tournament;
    private ArrayList<String> players;
    private ArrayList<Round> rounds;



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

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }
}
