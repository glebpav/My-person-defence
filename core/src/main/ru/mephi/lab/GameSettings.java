package ru.mephi.lab;

public class GameSettings {

    public static final int TICK_DURATION = 60;

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;

    public static int CELL_WIDTH = 128;
    public static int CELL_HEIGHT = 128;

    public static int CONSTRUCTION_WIDTH = 64;
    public static int CONSTRUCTION_HEIGHT = 64;

    public static int CASTLE_WIDTH = 64;
    public static int CASTLE_HEIGHT = 64;

    public static int CHARACTER_WIDTH = 64;
    public static int CHARACTER_HEIGHT = 64;


    // Health points

    public static float FENCE_DEFAULT_HEALTH_POINTS = 5;

    // Fence damage by enemies

    public static float HEAVY_INFANTRY_FENCE_DAMAGE = 10;
    public static float AVIATION_FENCE_DAMAGE = 10;

    // Default damage by Tower

    public static float TOWER_DAMAGE_LEVEL_1 = 10;
    public static float TOWER_DAMAGE_LEVEL_2 = 15;
    public static float TOWER_DAMAGE_LEVEL_3 = 20;

    // Attack radius by Tower

    public static float TOWER_ATTACK_RADIUS_LEVEL_1 = 2;
    public static float TOWER_ATTACK_RADIUS_LEVEL_2 = 3;
    public static float TOWER_ATTACK_RADIUS_LEVEL_3 = 4;


    public static final String SKINS_PATH = "gameResources/skins/";
    public static final String TILES_PATH = "gameResources/tiles/";

    public static final boolean DEBUG_MODE = true;
    public static boolean TEST_MODE = false;

}
