package ru.mephi.lab.actor;

import java.util.*;

/**
 * 
 */
public class Position {

    /**
     * Default constructor
     */
    public Position() {
    }

    /**
     * 
     */
    public float x;

    /**
     * 
     */
    public float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}