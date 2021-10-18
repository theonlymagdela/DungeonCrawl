package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.common.Properties;

public class Manuscript extends Item {
    public Manuscript(Cell cell, Properties properties) {
        super(cell, properties);
    }

    @Override
    public String getTileName() {
        return "manuscript";
    }
}
