package level;

import com.badlogic.gdx.scenes.scene2d.Actor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mephi.lab.actor.ActorType;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.constructions.Castle;
import ru.mephi.lab.actor.constructions.Fence;
import ru.mephi.lab.actor.constructions.Lair;
import ru.mephi.lab.actor.constructions.Tower;
import ru.mephi.lab.actor.enemy.Aviation;
import ru.mephi.lab.actor.enemy.HeavyInfantry;
import ru.mephi.lab.actor.enemy.LightInfantry;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.cell.MountainCell;
import ru.mephi.lab.cell.PlainCell;
import ru.mephi.lab.cell.WaterCell;
import ru.mephi.lab.level.GameConstructions;
import ru.mephi.lab.level.GameField;
import ru.mephi.lab.utils.geometry.GeometryHelper;
import ru.mephi.lab.utils.lair.EnemyArray;

import java.util.ArrayList;
import java.util.Arrays;

public class GameFieldTest {

    GameField gameField;

    @BeforeEach
    void prepareData() {
        gameField = new GameField(10, 10);
        GameConstructions gameConstructions = new GameConstructions();

        int[][] matrix = {
                {2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 1, 1, 1, 1, 1, 1, 2, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 2, 1},
                {1, 1, 1, 1, 1, 3, 1, 2, 2, 1},
                {1, 1, 1, 2, 3, 3, 3, 3, 2, 1},
                {1, 1, 2, 2, 3, 3, 1, 1, 1, 1},
                {1, 1, 1, 1, 3, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 3, 1, 3, 1, 1, 1},
                {1, 1, 1, 1, 3, 1, 3, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 3, 1, 1, 1}
        };

        for (int i = gameField.fieldWidth - 1; i >= 0; i--) {
            for (int j = gameField.fieldHeight - 1; j >= 0; j--) {

                Position position = GeometryHelper.convertCoords(i, j);
                int x = (int) position.x;
                int y = (int) position.y;

                gameField.field.setCell(
                        i, j,
                        switch (matrix[i][j]) {
                            case 1 -> new PlainCell(x, y);
                            case 2 -> new MountainCell(x, y);
                            case 3 -> new WaterCell(x, y);
                            default -> new PlainCell(x, y);
                        }
                );
            }
        }

        // lairs

        Cell cell = gameField.field.getCell(0, 1);
        Cell cell1 = gameField.field.getCell(1, 0);

        float fx = cell.getX();
        float fx2 = cell1.getX();
        float fy = cell.getY();
        float fy2 = cell1.getY();

        EnemyArray enemyArray = new EnemyArray(2, new ArrayList<>(Arrays.asList(new LightInfantry(fx, fy))));
        EnemyArray enemyArray2 = new EnemyArray(20, new ArrayList<>(Arrays.asList(new HeavyInfantry(fx, fy))));
        EnemyArray enemyArray3 = new EnemyArray(7, new ArrayList<>(Arrays.asList(new Aviation(fx2, fy2))));

        Lair lair = new Lair(fx, fy);
        lair.fieldPosition.setPosition(0, 1);
        lair.addEnemies(enemyArray);
        lair.addEnemies(enemyArray2);

        Lair lair2 = new Lair(fx2, fy2);
        lair.fieldPosition.setPosition(1, 0);
        lair.addEnemies(enemyArray3);

        cell.addActor(lair);
        cell1.addActor(lair2);
        gameConstructions.addLair(lair);
        gameConstructions.addLair(lair2);

        // castle

        cell = gameField.field.getCell(9, 9);

        fx = cell.getX();
        fy = cell.getY();

        Castle castle = new Castle(0, fx, fy);
        cell.addActor(castle);
        gameConstructions.setCastle(castle);

        // fence

        cell = gameField.field.getCell(4, 9);

        fx = cell.getX();
        fy = cell.getY();

        Fence fence = new Fence(fx, fy);
        cell.addActor(fence);
        gameConstructions.addFence(fence);

        cell = gameField.field.getCell(5, 9);

        fx = cell.getX();
        fy = cell.getY();

        fence = new Fence(fx, fy);
        cell.addActor(fence);
        gameConstructions.addFence(fence);

        cell = gameField.field.getCell(3, 9);

        fx = cell.getX();
        fy = cell.getY();

        fence = new Fence(fx, fy);
        cell.addActor(fence);
        gameConstructions.addFence(fence);

        cell = gameField.field.getCell(8, 9);

        fx = cell.getX();
        fy = cell.getY();

        Tower tower = new Tower(fx, fy);
        cell.addActor(tower);
        gameConstructions.addTower(tower);

    }

    @Test
    void setCeilActor() {
        gameField.setCeilActor(0, 0, new Fence(0, 0));
        Assertions.assertEquals(gameField.getCeilActor(0, 0).get(0).actorType, ActorType.FENCE);
    }

    @Test
    void getCeilActor() {
        gameField.setCeilActor(0, 0, new Tower(0, 0));
        Assertions.assertEquals(gameField.getCeilActor(0, 0).get(0).actorType, ActorType.TOWER);
    }

    @Test
    void getAllCells() {
        ArrayList<Actor> actorList = gameField.getAllCells();
        int fieldSize = gameField.fieldHeight * gameField.fieldWidth;
        Assertions.assertEquals(actorList.size(), fieldSize);
    }

    @Test
    void getAllActors() {
        ArrayList<BaseActor> listOfAllActors;
        listOfAllActors = gameField.getAllActors();
        Assertions.assertEquals(listOfAllActors.size(), 7);
    }

}
