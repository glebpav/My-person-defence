package ru.mephi.lab.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.mephi.lab.actor.abilities.Damageable;
import ru.mephi.lab.actor.abilities.Hittable;
import ru.mephi.lab.actor.abilities.Indescribable;
import ru.mephi.lab.actor.abilities.Updatable;
import ru.mephi.lab.level.GameField;

import java.util.ArrayList;


public class BaseActor extends Actor implements Damageable, Hittable {

    protected DrawingType drawingType;
    protected transient Texture texture;
    protected transient ArrayList<Texture> texturesArray;

    protected int selectedFrameIdx;
    protected String texturePath;
    protected ArrayList<String> texturePathArray;
    protected int[][] spriteCoords;

    public ActorType actorType;

    public double healthPoints;

    public BaseActor(float x, float y) {
        setX(x);
        setY(y);

        drawingType = DrawingType.ONE_TEXTURE;
        fieldPosition = new Position();
    }

    public Position fieldPosition;

    @SuppressWarnings("NewApi")
    public void loadTexture() {
        switch (drawingType) {
            case ONE_TEXTURE -> {
                texture = new Texture(texturePath);
            }
            case UPDATABLE_TEXTURE, ANIMATING_TEXTURE -> {
                texturesArray = new ArrayList<>();
                texturePathArray.forEach(path -> texturesArray.add(new Texture(path)));
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

    @Override
    public float makeDamage() {
        return 0;
    }

    @Override
    public float makeDamageFence() {
        return 0;
    }

    @Override
    public boolean shouldAttack(int castleX, int castleY) {
        return false;
    }

    @Override
    public BaseActor shouldAttackFence(GameField gameField) {
        return null;
    }

    @Override
    public void regenerateHitPoints() {

    }

    @Override
    public boolean getDamage(double damage) {
        healthPoints -= damage;
        // System.out.println("left healthPoints: " + healthPoints);
        return healthPoints <= 0;
    }

    @Override
    public boolean isAlive() {
        return healthPoints > 0;
    }

}