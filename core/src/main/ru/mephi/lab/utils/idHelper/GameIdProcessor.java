package ru.mephi.lab.utils.idHelper;

import com.google.gson.Gson;
import ru.mephi.lab.utils.files.FileHelper;

import java.io.IOException;

public class GameIdProcessor {

    private final static String tokenDescriptorPath = "assets/systemFiles/tokenDescriptor.json";
    public IdHandler idHandler;

    public IdHandler loadSavedGames() {
        try {
            String json = FileHelper.readJsonFile(tokenDescriptorPath);
            Gson gson = new Gson();
            idHandler = gson.fromJson(json, IdHandler.class);
            return idHandler;
        } catch (IOException ignored) {
            return null;
        }
    }

    public static String getGamePath(String gameId) {
        try {
            String json = FileHelper.readJsonFile(tokenDescriptorPath);
            Gson gson = new Gson();
            IdHandler idHandler = gson.fromJson(json, IdHandler.class);
            return idHandler.getGamePathById(gameId);
        } catch (IOException e) {
            return "";
        }
    }

}
