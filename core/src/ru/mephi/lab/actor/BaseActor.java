package ru.mephi.lab.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.mephi.lab.actor.abilities.Indescribable;

import java.util.ArrayList;


public class BaseActor extends Actor implements Indescribable {

    protected DrawingType drawingType;

    public ActorType actorType;

    protected transient Texture texture;
    protected transient ArrayList<Texture> texturesArray;

    protected int selectedFrameIdx;
    protected String texturePath;
    protected ArrayList<String> texturePathArray;
    protected int[][] spriteCoords;

    public BaseActor(float x, float y) {
        setX(x);
        setY(y);

        drawingType = DrawingType.ONE_TEXTURE;
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

    @SuppressWarnings("NewApi")
    public void loadTexture() {
        switch (drawingType) {
            case ONE_TEXTURE -> {
                texture = new Texture(texturePath);
            }
            case UPDATABLE_TEXTURE, ANIMATING_TEXTURE -> {
                texturesArray = new ArrayList<>();
                texturePathArray.forEach(path -> texturesArray.add(new Texture(path)));
                System.out.println("here");
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        switch (drawingType) {
            case ONE_TEXTURE -> {
                if (texture != null) batch.draw(texture, getX(), getY(), getWidth(), getHeight());
            }
            case UPDATABLE_TEXTURE -> {
                if (texturesArray.size() > selectedFrameIdx && selectedFrameIdx >= 0 && texturesArray.get(selectedFrameIdx) != null) {
                    batch.draw(texturesArray.get(selectedFrameIdx), getX(), getY(), getWidth(), getHeight());
                }
            }
            case ANIMATING_TEXTURE -> {

            }
        }


    }
}