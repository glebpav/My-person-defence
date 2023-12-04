package ru.mephi.lab.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.mephi.lab.MyGdxGame;
import ru.mephi.lab.level.GameSession;

import java.util.ArrayList;

import static ru.mephi.lab.GameSettings.*;

public class GameScreen extends BaseScreen {

    GameSession gameSession;

    public GameScreen(MyGdxGame myGdxGame) {
        super(myGdxGame);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        handleInput();
        gameSession.makeGameStep();
    }

    @Override
    public void show() {
        super.show();

        myGdxGame.camera.position.set((float) CELL_WIDTH / 2, (float) (gameSession.field.heightInPixels + CELL_HEIGHT / 2), 0);
        myGdxGame.camera.update();
    }

    @SuppressWarnings("NewApi")
    public void loadSession(String gameId) {
        gameSession = new GameSession(gameId);
        gameSession.setOnFieldChangedListener(onFieldChanged);
        gameSession.startGame();
        gameSession.getAllActors();
    }

    @SuppressWarnings("NewApi")
    GameSession.OnFieldChanged onFieldChanged = newActors -> {
        System.out.println("add new enemy");
        System.out.println(newActors.size());
        System.out.println(newActors.get(0).getX());
        System.out.println(newActors.get(0).getY());

        newActors.forEach(actor -> stage.addActor(actor));
    };


    public void handleInput() {

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            myGdxGame.camera.zoom -= 0.003f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            myGdxGame.camera.zoom += 0.003f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.P)) {
            System.out.println(myGdxGame.camera.position);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            myGdxGame.camera.position.x -= 1;
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            myGdxGame.camera.position.x += 1;
        }else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            myGdxGame.camera.position.y += 1;
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            myGdxGame.camera.position.y -= 1;
        }

    }

}
