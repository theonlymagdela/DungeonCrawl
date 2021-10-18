package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.common.Properties;

public class Skeleton extends Actor {
    public Skeleton(Cell cell, Properties properties) {
        super(cell, properties);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
