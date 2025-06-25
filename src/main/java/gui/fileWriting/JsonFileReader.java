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

    public static void main(String[] args) {
        // Test code can go here if needed
    }

    public static void readJsonFromFile(File file, Game game) throws IOException {
        System.out.println("Reading JSON from file: " + file.getAbsolutePath());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);

        game.setYoutubeLink(jsonNode.get("youtubeLink").asText());
        game.setOwnTeam(jsonNode.get("ownTeam").asText());
        game.setEnemyTeam(jsonNode.get("enemyTeam").asText());
        game.setTournament(jsonNode.get("tournament").asText());
        game.setPlayers(
            objectMapper.readValue(jsonNode.get("players").toString(), new TypeReference<ArrayList<String>>() {})
        );
        game.setRounds(objectMapper.readValue(jsonNode.get("rounds").asText());

        game.setHowManyRounds(jsonNode.get("howManyRounds").asInt());
    }
}
