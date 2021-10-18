package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class GameMap {
    private static final String[] mapList = {"/map.txt", "/mapForest.txt", "/mapWater.txt"};
    private final int width;
    private final int height;
    private final Cell[][] cells;
    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public static String getMapName(int index) {
        return mapList[index];
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
