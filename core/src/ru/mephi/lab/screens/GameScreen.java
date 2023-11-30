package ru.mephi.lab.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.mephi.lab.MyGdxGame;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.level.GameSession;

import java.util.ArrayList;

public class GameScreen extends BaseScreen {

    GameSession gameSession;

    public GameScreen(MyGdxGame myGdxGame) {
        super(myGdxGame);

    }

    public void loadSession(String gameId) {
        gameSession = new GameSession(gameId);
        gameSession.startGame();
        ArrayList<Actor> cellsActors = gameSession.field.getAllActors();

        for(Actor actor : cellsActors) {
            stage.addActor(actor);
        }
    }

}
