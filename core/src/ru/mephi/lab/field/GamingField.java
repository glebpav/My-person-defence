package ru.mephi.lab.field;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import ru.mephi.lab.actor.Actor;
import ru.mephi.lab.cell.Cell;
import ru.mephi.lab.utils.vector.MyVector;

import java.util.*;


public class GamingField {


    public GamingField() {

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
        // TODO implement here
        return null;
    }

    public void load(String path) {
        // Gson gson = new Gson();
        // gson.fromJson(json)
    }

    public void save(String path) {

    }

}