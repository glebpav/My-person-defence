package ru.mephi.lab.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.mephi.lab.MyGdxGame;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.level.GameSession;

import java.util.ArrayList;
import java.util.Collections;

public class GameScreen extends BaseScreen {

    GameSession gameSession;

    public GameScreen(MyGdxGame myGdxGame) {
        super(myGdxGame);

    }

    public void loadSession(String gameId) {
        gameSession = new GameSession(gameId);
        gameSession.startGame();
        System.out.println("filed: " + gameSession.field);
        ArrayList<Actor> cellsActors = gameSession.field.getAllActors();
        System.out.println(cellsActors.toString());
        for(Actor actor : cellsActors) {
            stage.addActor(actor);
        }
    }

}
