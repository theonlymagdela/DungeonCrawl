package com.codecool.dungeoncrawl.logic.common;

public class Properties {
    private int health;
    private float strength;  // Health bonus when attacking given in percent.
    private float defence;   // Health bonus when defending given in percent

    public Properties(int health, float strength, float defence) {
        this.health = health;
        this.strength = strength;
        this.defence = defence;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getDefence() {
        return defence;
    }

    public void setDefence(float defence) {
        this.defence = defence;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }
}
