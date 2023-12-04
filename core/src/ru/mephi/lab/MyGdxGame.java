package ru.mephi.lab;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.constructions.Castle;
import ru.mephi.lab.actor.constructions.Lair;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.cell.MountainCell;
import ru.mephi.lab.cell.PlainCell;
import ru.mephi.lab.cell.WaterCell;
import ru.mephi.lab.level.GameField;
import ru.mephi.lab.screens.GameScreen;
import ru.mephi.lab.screens.MenuScreen;
import ru.mephi.lab.utils.files.JsonProcessor;
import ru.mephi.lab.utils.geometry.GeometryHelper;
import ru.mephi.lab.utils.idHelper.GameIdProcessor;

import java.util.Random;

import static ru.mephi.lab.GameSettings.*;

public class MyGdxGame extends Game {

    public GameIdProcessor gameIdProcessor;

    public Skin skin;
    public OrthographicCamera camera;
    public ExtendViewport viewport;

    public GameScreen gameScreen;
    public MenuScreen menuScreen;

    @Override
    public void create() {

        GameField gameField = new GameField(10, 10);
        Random rn = new Random();

        int [][] matrix = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 2, 2},
                {1, 1, 1, 1, 1, 3, 1, 2, 2, 1},
                {1, 1, 1, 2, 3, 3, 3, 3, 2, 1},
                {1, 1, 2, 2, 3, 3, 2, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        for (int i = 9; i >= 0; i--) {
            for (int j = 9; j >= 0; j--) {

                Position position = GeometryHelper.convertCoords(i, j);
                int x = (int) position.x;
                int y = (int) position.y;

                gameField.field.setCell(i, j,
                        switch (matrix[i][j]) {
                            case 1 -> new PlainCell(x, y);
                            case 2 -> new MountainCell(x, y);
                            case 3 -> new WaterCell(x, y);
                            default -> new PlainCell(x, y);
                        }
                );
            }
        }

        Cell cell = gameField.field.getCell(0, 0);

        float fx = cell.getX() + (CELL_WIDTH - LAIR_WIDTH) / 2f;
        float fy = cell.getY() + (0.75f * CELL_HEIGHT - 0.5f * LAIR_HEIGHT + Lair.Y_OFFSET);

        cell.setActor(new Lair(fx, fy));

        cell = gameField.field.getCell(9, 9);

        fx = cell.getX() + (CELL_WIDTH - CASTLE_WIDTH) / 2f;
        fy = cell.getY() + (0.75f * CELL_HEIGHT - 0.5f * CASTLE_HEIGHT + Castle.Y_OFFSET);

        cell.setActor(new Castle(0, fx, fy));

        JsonProcessor.serializeObjectAndWrite("assets/systemFiles/gameSaves/gameAFG2Bae323vr/field.json", gameField);


        camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);

        skin = new Skin(Gdx.files.internal(SKINS_PATH + "default/skin/uiskin.json"));
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        gameIdProcessor = new GameIdProcessor();
        gameIdProcessor.loadSavedGames();

        gameScreen = new GameScreen(this);
        menuScreen = new MenuScreen(this);

        setScreen(menuScreen);

    }

    @Override
    public void dispose() {
        skin.dispose();
    }
}
