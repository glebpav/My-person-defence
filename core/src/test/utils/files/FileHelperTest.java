package utils.files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mephi.lab.utils.files.FileHelper;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHelperTest {

    @Test()
    void readJsonFile() {

        try {
            FileHelper.readJsonFile("assets/systemFiles/gameSaves/ADSFdfdd.json");
        } catch (IOException e) {
            return;
        }

    }

    @Test
    void writeJsonFile() {
        try {
            FileHelper.writeJsonFile("aaa.png", "hello");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
