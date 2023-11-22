package ru.mephi.lab.utils.files;

import java.io.*;

public class FileHelper {

    public static String readJsonFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder outputString = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            outputString.append(line);
        }
        return outputString.toString();
    }

    public static void writeJsonFile(String path, String writingString) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(writingString);
        writer.close();
    }

}
