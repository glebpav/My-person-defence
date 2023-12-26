package ru.mephi.lab.cell;

import com.badlogic.gdx.graphics.Texture;
import ru.mephi.lab.actor.BaseActor;

import static ru.mephi.lab.GameSettings.TILES_PATH;

public class PlainCell extends Cell{

    public PlainCell(int x, int y) {
        super(x, y);
        texturePath = TILES_PATH + "cells/plane.png";
        try{
            texture = new Texture(texturePath);
        } catch (Exception ignored) {

        }
        cellType = CellType.PLANE;
    }

    // TODO: implement this
    @Override
    public void addActor(BaseActor baseActor) {
        super.addActor(baseActor);
    }
}
