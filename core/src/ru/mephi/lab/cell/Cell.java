package ru.mephi.lab.cell;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.mephi.lab.actor.BaseActor;

import static ru.mephi.lab.GameSettings.CELL_HEIGHT;
import static ru.mephi.lab.GameSettings.CELL_WIDTH;


public class Cell extends Actor {

    public transient Texture texture;
    String texturePath;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public Cell(int x, int y) {
        setBounds(x, y, CELL_WIDTH, CELL_HEIGHT);
    }

    public void loadTexture() {
        if (!texturePath.isEmpty()) {
            texture = new Texture(texturePath);
        }

        setWidth(CELL_WIDTH);
        setHeight(CELL_HEIGHT);
    }

    public BaseActor baseActor;

    public void setActor(BaseActor baseActor) {
        this.baseActor = baseActor;
    }
}