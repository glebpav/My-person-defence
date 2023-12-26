package ru.mephi.lab.cell;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.mephi.lab.actor.BaseActor;

import java.util.ArrayList;

import static ru.mephi.lab.GameSettings.CELL_HEIGHT;
import static ru.mephi.lab.GameSettings.CELL_WIDTH;


public class Cell extends Actor {

    public transient Texture texture;
    String texturePath;

    public CellType cellType;
    public ArrayList<BaseActor> actorsList;

    public Cell(int x, int y) {
        setBounds(x, y, CELL_WIDTH, CELL_HEIGHT);
        actorsList = new ArrayList<>();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void loadTexture() {
        if (!texturePath.isEmpty()) {
            try {
                texture = new Texture(texturePath);
            } catch (Exception ignored) {

            }
        }

        setWidth(CELL_WIDTH);
        setHeight(CELL_HEIGHT);
    }

    public void addActor(BaseActor baseActor) {
        actorsList.add(baseActor);
    }

    public void removeActor(BaseActor baseActor) {
        actorsList.remove(baseActor);
    }

}