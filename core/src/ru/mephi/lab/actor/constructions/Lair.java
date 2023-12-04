package ru.mephi.lab.actor.constructions;

import com.badlogic.gdx.graphics.Texture;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.actor.DrawingType;
import ru.mephi.lab.actor.Position;
import ru.mephi.lab.utils.lair.EnemyArray;

import java.util.*;

import static ru.mephi.lab.GameSettings.*;

/**
 * 
 */
public class Lair extends BaseActor {

    public final static int Y_OFFSET = 14;

    public Lair(float x, float y) {
        super(x, y);
        setWidth(LAIR_WIDTH);
        setHeight(LAIR_HEIGHT);
        texturePath = TILES_PATH + "lair/lair.png";
        loadTexture();

        drawingType = DrawingType.ONE_TEXTURE;
    }

    public ArrayList<EnemyArray> enemyAppearanceTime;

    public void spawnEnemy(int currentTime) {
        // TODO implement here
    }

}