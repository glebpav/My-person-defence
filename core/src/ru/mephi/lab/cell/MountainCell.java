package ru.mephi.lab.cell;

import com.badlogic.gdx.graphics.Texture;
import ru.mephi.lab.actor.BaseActor;

import static ru.mephi.lab.GameSettings.TILES_PATH;

public class MountainCell extends Cell{

    public MountainCell(int x, int y) {
        super(x, y);
        texturePath = TILES_PATH + "cells/lava.png";
        texture = new Texture(texturePath);
        cellType = CellType.MOUNTAIN;
    }

    @Override
    public void addActor(BaseActor baseActor) {
        super.addActor(baseActor);
    }
}
