package ru.mephi.lab.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;
import ru.mephi.lab.MyGdxGame;

public class MenuScreen extends BaseScreen {

    public MenuScreen(final MyGdxGame myGdxGame) {
        super(myGdxGame);

        Table rootTable = new Table();
        Button startButton = new TextButton("Start this game", myGdxGame.skin);
        Button exitButton = new TextButton("Exit game", myGdxGame.skin);
        Button settingsButton = new TextButton("Settings", myGdxGame.skin);
        final List<String> select = new List<>(myGdxGame.skin);

        select.setItems(myGdxGame.gameIdProcessor.idHandler.getGamesArray());

        rootTable.setFillParent(true);

        rootTable.columnDefaults(2);
        rootTable.add(select).width(400).height(150).colspan(2).space(10);
        rootTable.row();
        rootTable.add(startButton).width(400).height(60).colspan(2).space(10);
        rootTable.row();
        rootTable.add(exitButton).width(195).height(60).space(10);
        rootTable.add(settingsButton).width(195).height(60).space(10);

        stage.addActor(rootTable);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(myGdxGame.gameIdProcessor.idHandler.getGamePathById(select.getSelected()));
                myGdxGame.gameScreen.loadSession(select.getSelected());
                myGdxGame.setScreen(myGdxGame.gameScreen);
            }
        });
    }


}
