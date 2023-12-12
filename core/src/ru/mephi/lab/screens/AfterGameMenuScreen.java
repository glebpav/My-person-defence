package ru.mephi.lab.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ru.mephi.lab.GameSettings;
import ru.mephi.lab.MyGdxGame;
import ru.mephi.lab.level.GameState;

public class AfterGameMenuScreen extends BaseScreen {

    Group loosedActorsGroup;
    Group wonActorsGroup;

    public AfterGameMenuScreen(MyGdxGame myGdxGame) {
        super(myGdxGame);

        loosedActorsGroup = new Group();
        wonActorsGroup = new Group();

        stage.addActor(loosedActorsGroup);
        stage.addActor(wonActorsGroup);

        initLoosedActorsGroup();
        initWonActorsGroup();
    }

    @Override
    public void show() {
        super.show();

        loosedActorsGroup.setVisible(false);
        wonActorsGroup.setVisible(false);

        if (myGdxGame.gameScreen.gameSession.state == GameState.LOOSED) {
            loosedActorsGroup.setVisible(true);
        } else if (myGdxGame.gameScreen.gameSession.state == GameState.ENDED) {
            wonActorsGroup.setVisible(true);
        }
    }

    public void initLoosedActorsGroup() {
        Table rootTable = new Table();
        Label label = new Label("Whoops, you loosed", myGdxGame.skin);
        Button startButton = new TextButton("go to menu", myGdxGame.skin);

        label.setFontScale(2);

        rootTable.setBounds(0, 0, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
        // rootTable.setFillParent(true);
        rootTable.add(label).height(60).spaceBottom(200);
        rootTable.row();
        rootTable.add(startButton).width(400).height(60);

        loosedActorsGroup.addActor(rootTable);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                myGdxGame.setScreen(myGdxGame.menuScreen);
            }
        });
    }

    public void initWonActorsGroup() {
        Table rootTable = new Table();
        Label label = new Label("Wu-aha, you won, congratulations", myGdxGame.skin);
        Button startButton = new TextButton("go to menu", myGdxGame.skin);

        label.setFontScale(2);

        rootTable.setBounds(0, 0, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
        // rootTable.setFillParent(true);
        rootTable.add(label).height(60).spaceBottom(200);
        rootTable.row();
        rootTable.add(startButton).width(400).height(60);

        wonActorsGroup.addActor(rootTable);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                myGdxGame.setScreen(myGdxGame.menuScreen);
            }
        });
    }
}
