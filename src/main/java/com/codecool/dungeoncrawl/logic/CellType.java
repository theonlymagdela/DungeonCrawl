package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    DOOR("door"),
    SKELETON("skeleton"),
    BUSH("bush"),
    WATER("water"),
    WATER2("water<"),
    OPENDOOR("openDoor"),
    CLOSEDHOLE("closedHole"),
    OPENEDHOLE("openedHole"),
    HOUSE("house"),
    WIZARD("wizard"),
    MUSHROOMS("mushrooms"),
    CABBAGE("cabbage");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
