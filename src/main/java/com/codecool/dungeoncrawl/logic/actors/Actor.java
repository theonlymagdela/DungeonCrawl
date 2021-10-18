package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.common.Properties;

public abstract class Actor implements Drawable {
    private Cell cell;
    private Properties properties;

    public Actor(Cell cell, Properties properties) {
        this.cell = cell;
        this.properties = properties;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public float getDamage() {
        return properties.getHealth() * (1 + (properties.getStrength() + properties.getDefence()));
    }

    public boolean isDead() {
        return properties.getHealth() <= 0;
    }
}
