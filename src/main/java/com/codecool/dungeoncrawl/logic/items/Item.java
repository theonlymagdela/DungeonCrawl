package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.common.Properties;

public abstract class Item implements Drawable {
    private final Cell cell;
    private final Properties properties;

    public Item(Cell cell, Properties properties) {
        this.cell = cell;
        this.cell.setItem(this);
        this.properties = properties;
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
}
