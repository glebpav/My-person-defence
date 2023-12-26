package ru.mephi.lab.level;

import ru.mephi.lab.actor.constructions.Castle;
import ru.mephi.lab.actor.constructions.Fence;
import ru.mephi.lab.actor.constructions.Lair;
import ru.mephi.lab.actor.constructions.Tower;

import java.util.ArrayList;

public class GameConstructions {

    public ArrayList<Lair> lairArray;
    public ArrayList<Fence> fenceArray;
    public ArrayList<Tower> towersArray;
    public Castle castle;

    public GameConstructions() {
        lairArray = new ArrayList<>();
        fenceArray = new ArrayList<>();
        towersArray = new ArrayList<>();
    }

    public void addLair(Lair lair) {
        lairArray.add(lair);
    }

    public void addFence(Fence fence) {
        fenceArray.add(fence);
    }

    public void addTower(Tower tower) {
        towersArray.add(tower);
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

}
