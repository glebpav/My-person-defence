package ru.mephi.lab.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ru.mephi.lab.MyGdxGame;

public class MenuScreen extends BaseScreen {

    public MenuScreen(MyGdxGame myGdxGame) {
        super(myGdxGame);

        Button button = new TextButton("Hello there", myGdxGame.skin);
        button.setPosition(0, 0);
        stage.addActor(button);

    }



}
