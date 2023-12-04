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
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.cell.MountainCell;
import ru.mephi.lab.cell.PlainCell;
import ru.mephi.lab.cell.WaterCell;
import ru.mephi.lab.level.GameField;
import ru.mephi.lab.level.GameSession;
import ru.mephi.lab.screens.GameScreen;
import ru.mephi.lab.screens.MenuScreen;
import ru.mephi.lab.utils.files.JsonProcessor;
import ru.mephi.lab.utils.idHelper.GameIdProcessor;
import ru.mephi.lab.utils.idHelper.IdHandler;
import ru.mephi.lab.utils.vector.BaseVector;

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

        /*GameField gameField = new GameField(10, 10);
        Random rn = new Random();

        for (int i = 9; i >= 0; i--) {
            for (int j = 9; j >= 0; j--) {

                int x = (int) ((i - j) * (CELL_WIDTH / 2f));
                int y = (int) ((i + j) * (CELL_WIDTH / 4f));

                gameField.field.matrix.get(i).set(j,
                        switch (rn.nextInt(3)) {
                            case 0 -> new PlainCell(x, y);
                            case 1 -> new MountainCell(x, y);
                            case 2 -> new WaterCell(x, y);
                            default -> new PlainCell(x, y);
                        }
                );

                // gameField.field.matrix.get(i).set(j, new PlainCell((int) x, (int) y));
            }
        }

        JsonProcessor.serializeObjectAndWrite("assets/systemFiles/gameSaves/gameAFG2Bae323vr/field.json", gameField);*/

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
