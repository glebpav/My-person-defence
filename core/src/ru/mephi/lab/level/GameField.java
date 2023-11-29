package ru.mephi.lab.level;

import com.google.gson.annotations.SerializedName;
import ru.mephi.lab.actor.Actor;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.utils.vector.MyVector;


public class GameField {


    public GameField() {

    }

    @SerializedName("fieldCells")
    public MyVector <MyVector<Cell>> field;

    @SerializedName("width")
    public int fieldWidth;
    @SerializedName("height")
    public int fieldHeight;

    public void setCeilActor(int positionX, int positionY, Actor actor) {
        // TODO implement here
    }

    public Actor getCeilActor(int positionX, int positionY) {

        return null;
    }

}