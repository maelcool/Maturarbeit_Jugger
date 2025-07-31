package gui.fileWriting;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Main.Main;
import gui.controller.FileOeffnenController;
import gui.storeageClasses.Game;

import java.io.File;
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
    public static void writeStringAndArrayListToFile(String key, ArrayList value, ObjectNode jsonNode) throws IOException {
        jsonNode.set(key, objectMapper.valueToTree(value)); //Before it was just putting Strings instead of Json Object
    }

    public static void writeTheGameToFile(Game game) throws IOException{
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.removeAll();
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

        //writeStringAndStringToFile("rounds", game.rounds != null ? game.rounds.toString() : "[]");
        try {
            writeStringAndArrayListToFile("rounds", game.rounds, jsonNode);
            Main.Logger.info("Rounds: " + game.rounds);
        } catch (IOException e) {
            //TODO: Make a better Exception handling
            e.printStackTrace();
        }

        writeStringAndIntToFile("howManyRounds", game.howManyRounds, jsonNode);
        Main.Logger.info("END OF JSON FILE WRITER ROUDNS: " + game.rounds);
        JsonFileWriter.endFile(jsonNode);

    }

    public static void endFile(ObjectNode jsonNode) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(FileOeffnenController.selectedFile, jsonNode);
    }
}