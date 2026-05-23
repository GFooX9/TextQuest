package ru.yuurgu.textquest.model;

public final class CharacterStats {

    private final String name;
    private final int health;
    private final int strength;
    private final int damage;
    private final int magic;
    private final int defense;

    public CharacterStats(
            String name,
            int health,
            int strength,
            int damage,
            int magic,
            int defense
    ) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.damage = damage;
        this.magic = magic;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getDamage() {
        return damage;
    }

    public int getMagic() {
        return magic;
    }

    public int getDefense() {
        return defense;
    }
}
