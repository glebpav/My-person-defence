package ru.mephi.lab.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import ru.mephi.lab.MyGdxGame;

import javax.swing.plaf.nimbus.State;

import static ru.mephi.lab.GameSettings.SCREEN_HEIGHT;
import static ru.mephi.lab.GameSettings.SCREEN_WIDTH;

public class BaseScreen extends ScreenAdapter {

    Stage stage;
    MyGdxGame myGdxGame;

    public BaseScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        stage = new Stage(myGdxGame.viewport);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }
}
