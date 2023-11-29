package ru.mephi.lab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.mephi.lab.level.GameSession;

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
