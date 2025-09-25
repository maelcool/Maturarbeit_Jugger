package gui.fileWriting;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gui.storeageClasses.Game;
import gui.storeageClasses.Round;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class JsonFileReader {
    static ObjectMapper objectMapper = new ObjectMapper();
    

    /**
    * Reads a JSON file and fills the given Game object with the Information.
    * Reads youtube link, teams, tournament, players, rounds and number of rounds.
    * @param file the JSON file to read from
    * @param game the Game object to fill
    * @throws IOException if reading the file fails
    */
    public static void readJsonFromFile(File file, Game game) throws IOException {
        System.out.println("Reading JSON from file: " + file.getAbsolutePath());
        
        JsonNode jsonNode = objectMapper.readTree(file);

        game.youtubeLink = jsonNode.get("youtubeLink").asText();
        game.ownTeam = jsonNode.get("ownTeam").asText();
        game.enemyTeam = jsonNode.get("enemyTeam").asText();
        game.tournament = jsonNode.get("tournament").asText();
        ArrayList<String> playrArrayList = transformJsonNodeToArrayListString(jsonNode.get("players"));
        game.players = playrArrayList;
        ArrayList<Round> roundList = transformJsonNodeToArrayListRounds(jsonNode.get("rounds"));
        game.rounds = roundList;
        System.out.print("ROUNDLIST: " + roundList);
        System.out.print("Game.ROunds: " + game.rounds);

        game.howManyRounds = jsonNode.get("howManyRounds").asInt();
    }

    private static ArrayList<Round> transformJsonNodeToArrayListRounds(JsonNode jsonNode){
        if (jsonNode == null || jsonNode.isNull()) {
            main.Main.Logger.error("Rounds is null, in JsonFileReader");
            return new ArrayList<>();
        }

        try {
            String jsonString = jsonNode.toString();
            return objectMapper.readValue(jsonString, new TypeReference<ArrayList<Round>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); 
        }
    }

    private static ArrayList<String> transformJsonNodeToArrayListString(JsonNode jsonNode){
        if (jsonNode == null || jsonNode.isNull()) {
            main.Main.Logger.error("Players is null, in JsonFileReader");
            return new ArrayList<>();
        }

        try {
            String jsonString = jsonNode.toString();
            return objectMapper.readValue(jsonString, new TypeReference<ArrayList<String>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        }
}
