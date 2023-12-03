package ru.mephi.lab.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mephi.lab.actor.abilities.Indescribable;


public class BaseActor extends com.badlogic.gdx.scenes.scene2d.Actor implements Indescribable {

    transient Texture texture;
    String texturePath;

    public BaseActor() {

    }

    public Position position;

    public boolean readDescription(String path) {
        // TODO implement here
        return false;
    }

    public boolean writeDescription(String path) {
        // TODO implement here
        return false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}