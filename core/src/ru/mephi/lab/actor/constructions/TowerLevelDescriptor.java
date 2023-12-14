package ru.mephi.lab.actor.constructions;

import static ru.mephi.lab.GameSettings.*;

public enum TowerLevelDescriptor {

    LEVEL_1("castle_level_1.png", TOWER_DAMAGE_LEVEL_1, TOWER_ATTACK_RADIUS_LEVEL_1),
    LEVEL_2("castle_level_2.png", TOWER_DAMAGE_LEVEL_2, TOWER_ATTACK_RADIUS_LEVEL_2),
    LEVEL_3("castle_level_3.png", TOWER_DAMAGE_LEVEL_3, TOWER_ATTACK_RADIUS_LEVEL_3);

    public final String texturePath;
    public final float damage;
    public final float attackRadius;

    TowerLevelDescriptor(String texturePath, float damage, float attackRadius) {
        this.texturePath = texturePath;
        this.damage = damage;
        this.attackRadius = attackRadius;
    }

    public static TowerLevelDescriptor getLevelDescriptor(int level) {
        if (level == 1) return LEVEL_1;
        if (level == 2) return LEVEL_2;
        if (level == 3) return LEVEL_3;
        return LEVEL_1;
    }
}
