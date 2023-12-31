package ru.mephi.lab.cell;

import com.badlogic.gdx.graphics.Texture;
import ru.mephi.lab.actor.BaseActor;

import static ru.mephi.lab.GameSettings.TILES_PATH;

public class WaterCell extends Cell{

    public WaterCell(int x, int y) {
        super(x, y);
        texturePath = TILES_PATH + "cells/water.png";
        try{
            texture = new Texture(texturePath);
        } catch (Exception ignored) {

        }
        cellType = CellType.WATER;
    }

    // TODO: implement this
    @Override
    public void addActor(BaseActor baseActor) {
        super.addActor(baseActor);
    }
}
