package ru.mephi.lab.utils.geometry;

import ru.mephi.lab.actor.Position;
import ru.mephi.lab.actor.constructions.Lair;

import static ru.mephi.lab.GameSettings.*;

public class GeometryHelper {

    public static Position convertCoords(float inputX, float inputY) {
        float x = (inputX - inputY) * (CELL_WIDTH / 2f);
        float y = (inputX + inputY) * (CELL_WIDTH / 4f);
        return new Position(x, y);
    }

    public static Position convertCoordsToCellCenter(float inputX, float inputY) {
        Position position = convertCoords(inputX, inputY);
        position.x += (CELL_WIDTH - CONSTRUCTION_WIDTH) / 2f;
        position.y += (0.75f * CELL_HEIGHT - 0.5f * CONSTRUCTION_HEIGHT + Lair.Y_OFFSET);
        return position;
    }

}
