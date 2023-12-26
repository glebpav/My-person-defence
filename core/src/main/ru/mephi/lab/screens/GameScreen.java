package ru.mephi.lab.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import ru.mephi.lab.GameSettings;
import ru.mephi.lab.MyGdxGame;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.ui.Blackout;
import ru.mephi.lab.level.GameSession;
import ru.mephi.lab.level.GameState;

import java.util.ArrayList;

import static ru.mephi.lab.GameSettings.*;

public class GameScreen extends BaseScreen {

    GameSession gameSession;
    Group loosedStateGroup;

    public GameScreen(MyGdxGame myGdxGame) {
        super(myGdxGame);

        loosedStateGroup = new Group();
        loosedStateGroup.addActor(new Blackout());
        stage.addActor(loosedStateGroup);

        loosedStateGroup.setVisible(false);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        handleInput();

        if (gameSession.state == GameState.ACTIVE) {
            gameSession.makeGameStep();
        } else {
            myGdxGame.camera.position.set((float) SCREEN_WIDTH / 2, (float) SCREEN_HEIGHT / 2, 0);
            myGdxGame.setScreen(myGdxGame.afterGameMenuScreen);
        }
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

    GameSession.OnFieldChanged onFieldChanged = new GameSession.OnFieldChanged() {
        @SuppressWarnings("NewApi")
        @Override
        public void onAddActors(ArrayList<Actor> newActors) {
            newActors.forEach(actor -> stage.addActor(actor));
        }

        @SuppressWarnings("NewApi")
        @Override
        public void onAddBaseActors(ArrayList<BaseActor> newActors) {
            newActors.forEach(actor -> stage.addActor(actor));
            newActors.forEach(actor -> {
                // gameSession.field.setCeilActor((int) actor.getX(), (int) actor.getY(), actor);
            });
        }

        @Override
        public void onRemoveActors(ArrayList<Actor> removedActors) {
            for (Actor actor: stage.getActors()) {
                if (removedActors.contains(actor))
                    actor.remove();
            }
        }
    };


    public void handleInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            myGdxGame.camera.zoom -= 0.003f;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            myGdxGame.camera.zoom += 0.003f;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            myGdxGame.camera.position.x -= 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            myGdxGame.camera.position.x += 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            myGdxGame.camera.position.y += 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            myGdxGame.camera.position.y -= 1;
        }

    }

}
