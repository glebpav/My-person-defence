package utils.files;

import com.badlogic.gdx.utils.Json;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mephi.lab.level.GameField;
import ru.mephi.lab.utils.files.JsonProcessor;
import ru.mephi.lab.utils.idHelper.IdHandler;
import ru.mephi.lab.utils.way.FieldParser;

public class JsonProcessorTest {

    public static String rootDir = "C:/Users/Gleb/IdeaProjects/My-person-defence/";

    @Test
    void getDeserializedObject() {
        IdHandler field = JsonProcessor.getDeserializedObject(
                rootDir + "assets/systemFiles/tokenDescriptor.json",
                IdHandler.class
        );

        Assertions.assertNotNull(field);
    }

    @Test
    void serializeObjectAndWrite() {
        JsonProcessor.serializeObjectAndWrite(
                rootDir + "assets/tests/test.json",
                new GameField()
        );

        GameField field = JsonProcessor.getDeserializedObject(
                rootDir + "assets/tests/test.json",
                GameField.class
        );

        Assertions.assertNotNull(field);
    }

}
