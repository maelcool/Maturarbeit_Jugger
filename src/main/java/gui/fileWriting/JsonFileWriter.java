package gui.fileWriting;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gui.controller.FileOeffnenController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class JsonFileWriter {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static ObjectNode jsonNode = objectMapper.createObjectNode();

    public static void main(String[] args) throws IOException {

        jsonNode.put("country", "India");
        objectMapper.writeValue(new File("mydata.json"), jsonNode);
    }

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

    public static void endFile() throws IOException {
        objectMapper.writeValue(FileOeffnenController.selectedFile, jsonNode);
    }
}