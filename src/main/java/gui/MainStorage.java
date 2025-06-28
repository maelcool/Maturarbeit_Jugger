package gui;


import gui.storeageClasses.Game;
import gui.controller.FileOeffnenController;
import gui.fileWriting.JsonFileWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;

public class MainStorage {

    public static void writeToFile(Game game) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();

        //Game Data
        JsonFileWriter.writeStringAndStringToFile("youtubeLink", game.youtubeLink);
        JsonFileWriter.writeStringAndStringToFile("ownTeam", game.ownTeam);
        JsonFileWriter.writeStringAndStringToFile("enemyTeam", game.enemyTeam);
        JsonFileWriter.writeStringAndStringToFile("tournament", game.tournament);
        JsonFileWriter.writeStringAndStringToFile("players", game.players.toString());
        JsonFileWriter.writeStringAndStringToFile("rounds", game.rounds != null ? game.rounds.toString() : "[]");
        JsonFileWriter.writeStringAndIntToFile("howManyRounds", game.howManyRounds);

       JsonFileWriter.endFile();
    }
}
