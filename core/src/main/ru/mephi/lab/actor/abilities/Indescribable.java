package ru.mephi.lab.actor.abilities;

import java.util.*;

/**
 * 
 */
public interface Indescribable {

    /**
     * @param path 
     * @return
     */
    public boolean readDescription(String path);

    /**
     * @param path 
     * @return
     */
    public boolean writeDescription(String path);

}