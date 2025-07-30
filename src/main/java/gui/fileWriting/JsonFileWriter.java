package gui.fileWriting;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gui.controller.FileOeffnenController;
import gui.storeageClasses.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class JsonFileWriter {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static ObjectNode jsonNode = objectMapper.createObjectNode();

    

    public static void writeStringAndStringToFile(String key, String value) throws IOException {
        jsonNode.put(key, value);   
    }
    public static void writeStringAndIntToFile(String key, int value) throws IOException {
        jsonNode.put(key, value);
    }
    public static void writeStringAndBooleanToFile(String key, boolean value) throws IOException {
        jsonNode.put(key, value);
    }
    public static void writeStringAndArrayListToFile(String key, ArrayList<String> value) throws IOException {
        for (String item : value) {
           jsonNode.put(key, item);
        }
    }

    public static void writeTheGameToFile(Game game) throws IOException{
        writeStringAndStringToFile("youtubeLink", game.youtubeLink);
        writeStringAndStringToFile("ownTeam", game.ownTeam);
        writeStringAndStringToFile("enemyTeam", game.enemyTeam);
        writeStringAndStringToFile("tournament", game.tournament);
        writeStringAndStringToFile("players", game.players.toString());
        writeStringAndStringToFile("rounds", game.rounds != null ? game.rounds.toString() : "[]");
        writeStringAndIntToFile("howManyRounds", game.howManyRounds);
        JsonFileWriter.endFile();

    }

    public static void endFile() throws IOException {
        objectMapper.writeValue(FileOeffnenController.selectedFile, jsonNode);
    }
}