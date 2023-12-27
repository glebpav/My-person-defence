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


/**
 * Class that override common actor from libGDX
 */
public class BaseActor extends Actor implements Damageable, Hittable {

    /**
     * Drawing type of this actor on screen
     */
    protected DrawingType drawingType;
    /**
     * Field that contains texture witch shows in case ONE_TEXTURE
     */
    protected transient Texture texture;
    /**
     * Array that contains textures, it shows in case UPDATABLE_TEXTURE
     */
    protected transient ArrayList<Texture> texturesArray;

    /**
     * Field that show idx of frame in texturesArray in case UPDATABLE_TEXTURE
     */
    protected int selectedFrameIdx;
    /**
     * Path to texture in case ONE_TEXTURE
     */
    protected String texturePath;
    /**
     * Array witch contains paths to textures in case UPDATABLE_TEXTURE
     */
    protected ArrayList<String> texturePathArray;
    /**
     * Coordinates of sprites in texture region
     */
    protected int[][] spriteCoords;

    /**
     * 2D vector witch contains position of actor on the field (not screen) at this moment
     */
    public Position fieldPosition;

    /**
     * type of actor
     */
    public ActorType actorType;

    /**
     * count of health points for this actors
     */
    public double healthPoints;

    /**
     * Base constructor
     *
     * @param x - x position of actor on screen
     * @param y - y position of actor on screen
     */
    public BaseActor(float x, float y) {
        setX(x);
        setY(y);

        drawingType = DrawingType.ONE_TEXTURE;
        fieldPosition = new Position();
    }

    /**
     * Method that load texture to RAM
     */
    @SuppressWarnings("NewApi")
    public void loadTexture() {
        switch (drawingType) {
            case ONE_TEXTURE -> {
                try {
                    texture = new Texture(texturePath);
                } catch (Exception ignored) {

                }
            }
            case UPDATABLE_TEXTURE, ANIMATING_TEXTURE -> {
                texturesArray = new ArrayList<>();
                texturePathArray.forEach(path -> {
                    try {
                        texturesArray.add(new Texture(path));
                    } catch (Exception ignored) {

                    }
                });
            }
        }
    }

    /**
     * Method that make put this actor on the screen
     *
     * @param batch
     * @param parentAlpha The parent alpha, to be multiplied with this actor's alpha, allowing the parent's alpha to affect all
     *                    children.
     */
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

    /**
     * Base method that return damage that this actor can make to another alive actor
     * @return base damage
     */
    @Override
    public float makeDamage() {
        return 0;
    }

    /**
     * Base method that return damage that this actor can make to fence
     *
     * @return base fence damage
     */
    @Override
    public float makeDamageFence() {
        return 0;
    }

    /**
     * Method witch show if actor should attack castle on this step
     *
     * @param castleX - x position of castle
     * @param castleY - y position of castle
     * @return shouldAttack
     */
    @Override
    public boolean shouldAttack(int castleX, int castleY) {
        return false;
    }

    /**
     * Method witch show if actor should attack fence on this step
     *
     * @param gameField - object that contains field
     * @return found fence instance
     */
    @Override
    public BaseActor shouldAttackFence(GameField gameField) {
        return null;
    }

    /**
     * Base method that increase health points of actor
     */
    @Override
    public void regenerateHitPoints() {

    }

    /**
     * Base method witch is invoking when actor gets damage
     *
     * @param damage - count of damage witch actor gets
     * @return is actor is still alive
     */
    @Override
    public boolean getDamage(double damage) {
        healthPoints -= damage;
        // System.out.println("left healthPoints: " + healthPoints);
        return healthPoints <= 0;
    }

    /**
     * Base method that show if actor is still alive
     *
     * @return if actor alive
     */
    @Override
    public boolean isAlive() {
        return healthPoints > 0;
    }

}