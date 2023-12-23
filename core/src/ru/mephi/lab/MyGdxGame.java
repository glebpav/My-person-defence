package ru.mephi.lab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.constructions.Castle;
import ru.mephi.lab.actor.constructions.Fence;
import ru.mephi.lab.actor.constructions.Lair;
import ru.mephi.lab.actor.constructions.Tower;
import ru.mephi.lab.actor.enemy.Aviation;
import ru.mephi.lab.actor.enemy.Enemy;
import ru.mephi.lab.actor.enemy.HeavyInfantry;
import ru.mephi.lab.actor.enemy.LightInfantry;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.cell.MountainCell;
import ru.mephi.lab.cell.PlainCell;
import ru.mephi.lab.cell.WaterCell;
import ru.mephi.lab.level.GameConstructions;
import ru.mephi.lab.level.GameField;
import ru.mephi.lab.level.GameParams;
import ru.mephi.lab.level.GameSession;
import ru.mephi.lab.screens.AfterGameMenuScreen;
import ru.mephi.lab.screens.GameScreen;
import ru.mephi.lab.screens.MenuScreen;
import ru.mephi.lab.utils.files.JsonProcessor;
import ru.mephi.lab.utils.geometry.GeometryHelper;
import ru.mephi.lab.utils.idHelper.GameIdProcessor;
import ru.mephi.lab.utils.idHelper.IdHandler;
import ru.mephi.lab.utils.lair.EnemyArray;
import ru.mephi.lab.utils.vector.BaseVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static ru.mephi.lab.GameSettings.*;

public class MyGdxGame extends Game {

    public GameIdProcessor gameIdProcessor;

    public Skin skin;
    public OrthographicCamera camera;
    public ExtendViewport viewport;

    public GameScreen gameScreen;
    public MenuScreen menuScreen;
    public AfterGameMenuScreen afterGameMenuScreen;

    @Override
    public void create() {

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

        // saving

        JsonProcessor.serializeObjectAndWrite("assets/systemFiles/gameSaves/gameAFG2Brrr23vr/field.json", gameField);
        JsonProcessor.serializeObjectAndWrite("assets/systemFiles/gameSaves/gameAFG2Brrr23vr/params.json", gameParams);
        JsonProcessor.serializeObjectAndWrite("assets/systemFiles/gameSaves/gameAFG2Brrr23vr/constructions.json", gameConstructions);

        camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);

        skin = new Skin(Gdx.files.internal(SKINS_PATH + "default/skin/uiskin.json"));
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        gameIdProcessor = new GameIdProcessor();
        gameIdProcessor.loadSavedGames();

        gameScreen = new GameScreen(this);
        menuScreen = new MenuScreen(this);
        afterGameMenuScreen = new AfterGameMenuScreen(this);

        setScreen(menuScreen);

    }

    @Override
    public void dispose() {
        skin.dispose();
    }
}
