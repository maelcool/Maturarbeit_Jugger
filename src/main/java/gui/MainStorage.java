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
        JsonFileWriter.writeStringAndStringToFile("youtubeLink", game.getYoutubeLink());
        JsonFileWriter.writeStringAndStringToFile("ownTeam", game.getOwnTeam());
        JsonFileWriter.writeStringAndStringToFile("enemyTeam", game.getEnemyTeam());
        JsonFileWriter.writeStringAndStringToFile("tournament", game.getTournament());
        JsonFileWriter.writeStringAndStringToFile("players", game.getPlayers().toString());
        JsonFileWriter.writeStringAndStringToFile("rounds", game.getRounds() != null ? game.getRounds().toString() : "[]");
        JsonFileWriter.writeStringAndIntToFile("howManyRounds", game.getHowManyRounds());

       JsonFileWriter.endFile();
    }
}
