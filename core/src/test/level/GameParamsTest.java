package level;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mephi.lab.level.GameParams;

import static ru.mephi.lab.GameSettings.TICK_DURATION;

public class GameParamsTest {

    GameParams gameParams;

    @BeforeEach
    void prepareData() {
        gameParams = new GameParams(0);
    }

    @Test
    void getCurrentTick() {

        int countOfTicks = 60;

        for (int i = 0; i < countOfTicks; i++) {
            gameParams.nextTick();
        }

        Assertions.assertEquals(gameParams.getCurrentTick(), countOfTicks / TICK_DURATION);
    }

    @Test
    void getCurrentBank() {
        int countOfTimes = 10;
        int addingMoney = 10;

        for (int i = 0; i < countOfTimes; i++) {
            gameParams.addMoney(addingMoney);
        }

        Assertions.assertEquals(gameParams.getCurrentBank(), countOfTimes * addingMoney);
    }

    @Test
    void nextTick() {
        int countOfTicks = 60;

        for (int i = 0; i < countOfTicks; i++) {
            gameParams.nextTick();
        }

        Assertions.assertEquals(gameParams.getCurrentTick(), countOfTicks / TICK_DURATION);
    }

    @Test
    void addMoney() {
        int countOfTimes = 10;
        int addingMoney = 10;

        for (int i = 0; i < countOfTimes; i++) {
            gameParams.addMoney(addingMoney);
        }

        Assertions.assertEquals(gameParams.getCurrentBank(), countOfTimes * addingMoney);
    }
}
