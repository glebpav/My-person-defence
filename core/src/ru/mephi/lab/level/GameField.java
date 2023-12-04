package ru.mephi.lab.level;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.google.gson.annotations.SerializedName;
import ru.mephi.lab.actor.BaseActor;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.utils.vector.BaseMatrix;

import java.util.ArrayList;
import java.util.Collections;

import static ru.mephi.lab.GameSettings.CELL_WIDTH;


public class GameField {

    @SerializedName("fieldCells")
    public BaseMatrix<Cell> field;

    public transient int heightInPixels;

    @SerializedName("width")
    public int fieldWidth;
    @SerializedName("height")
    public int fieldHeight;

    public GameField(int fieldHeight, int fieldWidth) {
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;
        field = new BaseMatrix<>(fieldWidth, fieldHeight);
    }

    public GameField() {

    }

    public void setCeilActor(int positionX, int positionY, BaseActor baseActor) {
        // TODO implement here
    }

    public BaseActor getCeilActor(int positionX, int positionY) {

        return null;
    }

    public ArrayList<Actor> getAllCells() {
        ArrayList<Actor> actorsList = new ArrayList<>();

        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldHeight; j++) {

                // float x = (i - j) * (CELL_WIDTH / 2f);
                // float y = (i + j) * (CELL_WIDTH / 4f);

                field.getCell(i, j).loadTexture();
                actorsList.add(field.getCell(i, j));
            }
        }

        heightInPixels = (int) ((fieldHeight) * (CELL_WIDTH / 4f));
        Collections.reverse(actorsList);
        return actorsList;
    }

    public ArrayList<Actor> getAllActors() {
        ArrayList<Actor> actorsList = new ArrayList<>();

        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldHeight; j++) {

                // float x = (i - j) * (CELL_WIDTH / 2f);
                // float y = (i + j) * (CELL_WIDTH / 4f);

                field.getCell(i, j).loadTexture();

                if (field.getCell(i, j).baseActor != null) {
                    field.getCell(i, j).baseActor.loadTexture();
                    actorsList.add(field.getCell(i, j).baseActor);
                }
            }
        }

        Collections.reverse(actorsList);
        return actorsList;
    }

}