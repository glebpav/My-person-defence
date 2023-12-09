package ru.mephi.lab.utils.geometry;

import ru.mephi.lab.actor.Position;

import static ru.mephi.lab.GameSettings.CELL_WIDTH;

public class GeometryHelper {

    public static Position convertCoords(float inputX, float inputY) {
        float x = (inputX - inputY) * (CELL_WIDTH / 2f);
        float y = (inputX + inputY) * (CELL_WIDTH / 4f);
        return new Position(x, y);
    }

    public static Position reverseCoords(float inputX, float inputY) {
        int x = (int) (0.5 * (inputX / (CELL_WIDTH / 2f) + inputY / (CELL_WIDTH / 4f)));
        int y = (int) (0.5 * (inputY / (CELL_WIDTH / 4f) + inputX / (CELL_WIDTH / 2f)));
        return new Position(x, y);
    }

    public static Position convertCoords(float inputX, float inputY, float width, float height) {
        float x = (inputX - inputY) * (width / 2f);
        float y = (inputX + inputY) * (height / 4f);
        return new Position(x, y);
    }

}
