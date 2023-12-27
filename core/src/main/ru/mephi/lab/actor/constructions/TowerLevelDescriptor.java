package ru.mephi.lab.actor.constructions;

import static ru.mephi.lab.GameSettings.*;

/**
 * Enum that shows settings for tower levels
 */
public enum TowerLevelDescriptor {

    LEVEL_1("tower_level_1.png", TOWER_DAMAGE_LEVEL_1, TOWER_ATTACK_RADIUS_LEVEL_1),
    LEVEL_2("tower_level_2.png", TOWER_DAMAGE_LEVEL_2, TOWER_ATTACK_RADIUS_LEVEL_2),
    LEVEL_3("tower_level_3.png", TOWER_DAMAGE_LEVEL_3, TOWER_ATTACK_RADIUS_LEVEL_3);

    /**
     * path to texture of this level tower
     */
    public final String texturePath;
    /**
     * damage that tower make on this level
     */
    public final float damage;
    /**
     * radius of attack for tower on this level
     */
    public final float attackRadius;

    /**
     * Base constructor for tower
     * @param texturePath - path to texture of this level tower
     * @param damage - damage that tower make on this level
     * @param attackRadius - radius of attack for tower on this level
     */
    TowerLevelDescriptor(String texturePath, float damage, float attackRadius) {
        this.texturePath = texturePath;
        this.damage = damage;
        this.attackRadius = attackRadius;
    }

    /**
     * Method that return level descriptor by level idx
     * @param level - level idx
     * @return level descriptor
     */
    public static TowerLevelDescriptor getLevelDescriptor(int level) {
        if (level == 1) return LEVEL_1;
        if (level == 2) return LEVEL_2;
        if (level == 3) return LEVEL_3;
        return LEVEL_1;
    }
}
