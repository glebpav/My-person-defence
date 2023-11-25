package ru.mephi.lab.utils.files;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

public class JsonProcessor {

    public static <T> T getDeserializedObject(String path, Type objectType) {
        try {
            String objectString = FileHelper.readJsonFile(path);
            Gson gson = new Gson();
            return gson.fromJson(objectString, objectType);
        } catch (IOException e) {
            // throw new RuntimeException(e);
            return null;
        }
    }

    public static <T> boolean serializeObjectAndWrite(String path, T object) {
        Gson gson = new Gson();
        try {
            FileHelper.writeJsonFile(path, gson.toJson(object));
            return true;
        } catch (IOException e) {
            return false;
            // throw new RuntimeException(e);
        }
    }

}
