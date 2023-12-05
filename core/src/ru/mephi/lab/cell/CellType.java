package ru.mephi.lab.cell;

public enum CellType {

    MOUNTAIN(1),
    WATER(1),
    PLANE(1),
    IMPASSABLE_CONSTRUCTION(1),
    FENCE(1),
    CASTLE(0),
    DEFAULT(2);

    public final int priority;

    CellType(int priority) {
        this.priority = priority;
    }

}
