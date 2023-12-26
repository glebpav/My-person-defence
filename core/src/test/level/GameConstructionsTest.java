package level;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mephi.lab.actor.constructions.Castle;
import ru.mephi.lab.actor.constructions.Fence;
import ru.mephi.lab.actor.constructions.Lair;
import ru.mephi.lab.actor.constructions.Tower;

public class GameConstructionsTest {

    ru.mephi.lab.level.GameConstructions gameConstructions;

    @BeforeEach
    void prepareData() {
        gameConstructions = new ru.mephi.lab.level.GameConstructions();
    }

    @Test
    void addLair() {
        gameConstructions.addLair(new Lair(0, 0));
        gameConstructions.addLair(new Lair(0, 0));
        gameConstructions.addLair(new Lair(0, 0));

        Assertions.assertEquals(gameConstructions.lairArray.size(), 3);
    }

    @Test
    void addFence() {
        gameConstructions.addFence(new Fence(0, 0));
        gameConstructions.addFence(new Fence(0, 0));
        gameConstructions.addFence(new Fence(0, 0));
        gameConstructions.addFence(new Fence(0, 0));

        Assertions.assertEquals(gameConstructions.fenceArray.size(), 4);
    }

    @Test
    void addTower() {
        gameConstructions.addTower(new Tower(0, 0));
        gameConstructions.addTower(new Tower(0, 0));
        gameConstructions.addTower(new Tower(0, 0));
        gameConstructions.addTower(new Tower(0, 0));
        gameConstructions.addTower(new Tower(0, 0));

        Assertions.assertEquals(gameConstructions.towersArray.size(), 5);
    }

    @Test
    void setCastle() {
        Castle castle = new Castle(1, 0, 0);
        gameConstructions.setCastle(castle);

        Assertions.assertEquals(gameConstructions.castle, castle);
    }

}
