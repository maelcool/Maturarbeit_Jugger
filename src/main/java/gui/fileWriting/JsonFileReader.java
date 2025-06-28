package gui.fileWriting;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gui.storeageClasses.Game;
import gui.storeageClasses.Round;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class JsonFileReader {

    public static void main(String[] args) {
        // Test code can go here if needed
    }

    public static void readJsonFromFile(File file, Game game) throws IOException {
        System.out.println("Reading JSON from file: " + file.getAbsolutePath());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);

        game.youtubeLink = jsonNode.get("youtubeLink").asText();
        game.ownTeam = jsonNode.get("ownTeam").asText();
        game.enemyTeam = jsonNode.get("enemyTeam").asText();
        game.tournament = jsonNode.get("tournament").asText();
        //TODO: Verify the Text that it is compatible
        ArrayList<String> playrArrayList = transformJsonNodeToArrayList(jsonNode.get("players"));
        //TODO: Verify the Text that it is compatible 
        game.players = playrArrayList;
        ArrayList<Round> roundList = transformJsonNodeToArrayList(jsonNode.get("rounds"));
        game.rounds = roundList;

        game.howManyRounds = jsonNode.get("howManyRounds").asInt();
    }

    private static ArrayList transformJsonNodeToArrayList(JsonNode jsonNode){
        String[] items = null;
        String jsonNodeText = jsonNode.asText();
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(jsonNodeText);
        while (matcher.find()) {
            String content = matcher.group(1); // content inside brackets
            items = content.split(",");
        }
        if (items != null){
            ArrayList contentArrayList = new ArrayList<>(Arrays.asList(items));
            return contentArrayList;
        }
        //TODO: Check for no null at receiver
        return null;
    }
}
