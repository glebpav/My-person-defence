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
import ru.mephi.lab.cell.PlainCell;
import ru.mephi.lab.level.GameField;
import ru.mephi.lab.level.GameSession;
import ru.mephi.lab.screens.GameScreen;
import ru.mephi.lab.screens.MenuScreen;
import ru.mephi.lab.utils.files.JsonProcessor;
import ru.mephi.lab.utils.idHelper.GameIdProcessor;
import ru.mephi.lab.utils.idHelper.IdHandler;
import ru.mephi.lab.utils.vector.BaseVector;

import static ru.mephi.lab.GameSettings.*;

public class MyGdxGame extends Game {

	public GameIdProcessor gameIdProcessor;

	public Skin skin;
	OrthographicCamera camera;
	public ExtendViewport viewport;

	public GameScreen gameScreen;
	public MenuScreen menuScreen;

	@Override
	public void create () {

		GameField gameField = new GameField(10, 10);

		for (int i = 0; i < 10; i++) {
			gameField.field.matrix.add(new BaseVector<Cell>());
			for (int j = 0; j < 10; j++) {
				gameField.field.matrix.get(i).add(new PlainCell(j * CELL_WIDTH, i * CELL_HEIGHT));
			}
		}

		JsonProcessor.serializeObjectAndWrite("assets/systemFiles/gameSaves/gameAFG2Bae323vr/field.json", gameField);

		/*camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);

		skin = new Skin(Gdx.files.internal(SKINS_PATH + "default/skin/uiskin.json"));
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

		gameIdProcessor = new GameIdProcessor();
		gameIdProcessor.loadSavedGames();

		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);

		setScreen(menuScreen);*/

	}

	@Override
	public void dispose() {
		skin.dispose();
	}
}
