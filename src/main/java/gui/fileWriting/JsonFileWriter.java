package gui.fileWriting;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gui.controller.FileOeffnenController;
import gui.storeageClasses.Game;
import main.Main;
import java.io.IOException;
import java.util.ArrayList;


public class JsonFileWriter {
    private static ObjectMapper objectMapper = new ObjectMapper();


    public static void writeStringAndStringToFile(String key, String value, ObjectNode jsonNode) throws IOException {
        jsonNode.put(key, value);   
    }
    public static void writeStringAndIntToFile(String key, int value, ObjectNode jsonNode) throws IOException {
        jsonNode.put(key, value);
    }
    public static void writeStringAndBooleanToFile(String key, boolean value, ObjectNode jsonNode) throws IOException {
        jsonNode.put(key, value);
    }
    public static <T> void writeStringAndArrayListToFile(String key, ArrayList<T> value, ObjectNode jsonNode) throws IOException {
        jsonNode.set(key, objectMapper.valueToTree(value));
    }


    /**
    * Writes the whole Game object into the JSON file.
    * Handles merging in existing rounds if the current game has none.
    * Writes teams, players, tournament, youtube link, rounds and number of rounds.
    * @param game the Game object to write
    * @throws IOException if writing fails
    */
    public static void writeTheGameToFile(Game game) throws IOException{
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.removeAll(); //Essential so that the Rounds aren't stacked upon each other
        Main.Logger.info("Removed all from JsonNode");

        Main.Logger.info("WriteTheGame was called");

        Game existingGame = null;
        try {
            existingGame = objectMapper.readValue(FileOeffnenController.selectedFile, Game.class);
        } catch (Exception e) {
            Main.Logger.warn("Couldn't load existing game. File might be new or invalid.");
        }

        if ((game.rounds == null || game.rounds.isEmpty()) && existingGame != null) {
            game.rounds = existingGame.rounds;
            Main.Logger.info("Merged in existing rounds from file.");
        }   
        
        System.out.println("Players list: " + game.players);
        System.out.println("Players list class: " + game.players.getClass());
        writeStringAndStringToFile("youtubeLink", game.youtubeLink, jsonNode);
        writeStringAndStringToFile("ownTeam", game.ownTeam, jsonNode);
        writeStringAndStringToFile("enemyTeam", game.enemyTeam, jsonNode);
        writeStringAndStringToFile("tournament", game.tournament, jsonNode);
        

        writeStringAndArrayListToFile("players", game.players, jsonNode);

        try {
            writeStringAndArrayListToFile("rounds", game.rounds, jsonNode);
            Main.Logger.info("Rounds: " + game.rounds);
        } catch (IOException e) {
            Main.Logger.error(e);
            e.printStackTrace();
        }

        writeStringAndIntToFile("howManyRounds", game.howManyRounds, jsonNode);
        Main.Logger.info("END OF JSON FILE WRITER ROUDNS: " + game.rounds);
        JsonFileWriter.endFile(jsonNode);

    }
    /** 
    * Writes the ObjectNode to the file with pretty printing.
    * @param jsonNode the ObjectNode to save
    * @throws IOException if writing fails
    */
    public static void endFile(ObjectNode jsonNode) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(FileOeffnenController.selectedFile, jsonNode);
    }
}