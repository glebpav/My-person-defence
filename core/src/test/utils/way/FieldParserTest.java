package utils.way;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.constructions.Castle;
import ru.mephi.lab.actor.constructions.Fence;
import ru.mephi.lab.actor.constructions.Lair;
import ru.mephi.lab.actor.constructions.Tower;
import ru.mephi.lab.actor.enemy.Aviation;
import ru.mephi.lab.actor.enemy.EnemyType;
import ru.mephi.lab.actor.enemy.HeavyInfantry;
import ru.mephi.lab.actor.enemy.LightInfantry;
import ru.mephi.lab.cell.*;
import ru.mephi.lab.level.GameConstructions;
import ru.mephi.lab.level.GameField;
import ru.mephi.lab.level.GameParams;
import ru.mephi.lab.level.GameSession;
import ru.mephi.lab.utils.geometry.GeometryHelper;
import ru.mephi.lab.utils.lair.EnemyArray;
import ru.mephi.lab.utils.vector.BaseMatrix;
import ru.mephi.lab.utils.way.FieldParser;

import java.util.ArrayList;
import java.util.Arrays;

public class FieldParserTest {

    @Test
    void getWeight_aviation() {

        CellType cellTypeFrom = CellType.PLANE;
        CellType cellTypeTo = CellType.CASTLE;
        EnemyType enemyType = EnemyType.AVIATION;

        Assertions.assertEquals(
                FieldParser.getWeight(cellTypeFrom, cellTypeTo, enemyType),
                1
        );
    }

    @Test
    void getWeight_lightInfantry() {

        CellType cellTypeFrom = CellType.PLANE;
        CellType cellTypeTo = CellType.CASTLE;
        EnemyType enemyType = EnemyType.LIGHT_INFANTRY;

        Assertions.assertEquals(
                FieldParser.getWeight(cellTypeFrom, cellTypeTo, enemyType),
                1
        );
    }

    @Test
    void getWeight_heavyInfantry() {

        CellType cellTypeFrom = CellType.PLANE;
        CellType cellTypeTo = CellType.CASTLE;
        EnemyType enemyType = EnemyType.HEAVY_INFANTRY;

        Assertions.assertEquals(
                FieldParser.getWeight(cellTypeFrom, cellTypeTo, enemyType),
                1
        );
    }

   @Test
   void parseField() {

       GameField gameField = new GameField(10, 10);
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

       GameParams gameParams = new GameParams(100);

       GameSession gameSession = new GameSession("");
       gameSession.params = gameParams;
       gameSession.field = gameField;

       FieldParser.parseField(gameSession, EnemyType.AVIATION);

   }

}
