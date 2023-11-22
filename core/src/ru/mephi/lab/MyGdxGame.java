package ru.mephi.lab;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.google.gson.Gson;
import ru.mephi.lab.field.GamingField;
import ru.mephi.lab.utils.files.FileHelper;

import java.io.IOException;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		/*Gson gson = new Gson();
		GamingField gamingField = new GamingField();
		String json = gson.toJson(gamingField);
		try {
			FileHelper.writeJsonFile("assets/test.json", json);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}*/

		try {
			String output = FileHelper.readJsonFile("assets/test.json");
			System.out.println("output is: " + output);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

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
