package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.common.Properties;

public abstract class Obstacles implements Drawable {
    private final Cell cell;
    private final Properties properties;

    public Obstacles(Cell cell, Properties properties) {
        this.cell = cell;
        this.cell.setObstacles(this);
        this.properties = properties;
    }

    public Cell getCell() {
        return cell;
    }

    public Properties getProperties() {
        return properties;
    }
}
