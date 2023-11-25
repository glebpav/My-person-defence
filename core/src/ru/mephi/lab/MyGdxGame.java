package ru.mephi.lab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.mephi.lab.actor.constructions.Castle;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.cell.PlainCell;
import ru.mephi.lab.level.GameField;
import ru.mephi.lab.level.GameParams;
import ru.mephi.lab.level.GameSession;
import ru.mephi.lab.utils.files.FileHelper;
import ru.mephi.lab.utils.files.JsonProcessor;
import ru.mephi.lab.utils.vector.MyVector;

import java.io.IOException;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture texture;
	
	@Override
	public void create () {

		batch = new SpriteBatch();
		texture = new Texture("assets/gameResources/backgrounds/somethingBackground.png");

		GameSession session = new GameSession("1234");

		// invokes in show() life cycle method
		session.startGame();

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(texture, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
