package ru.mephi.lab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.mephi.lab.level.GameSession;
import ru.mephi.lab.utils.files.FileHelper;

import java.io.IOException;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();


		GameSession session = new GameSession("1234");

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
