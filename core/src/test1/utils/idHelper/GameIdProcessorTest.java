package test1.utils.idHelper;

import com.badlogic.gdx.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mephi.lab.utils.idHelper.GameIdProcessor;

public class GameIdProcessorTest {

    @Test
    void loadSavedGames() {
        GameIdProcessor gameIdProcessor = new GameIdProcessor();
        Assertions.assertNull(gameIdProcessor.loadSavedGames());
    }

    @Test
    void loadGamePath() {
        GameIdProcessor gameIdProcessor = new GameIdProcessor();
        Assertions.assertEquals(gameIdProcessor.getGamePath("AAA"), "");
    }

}
