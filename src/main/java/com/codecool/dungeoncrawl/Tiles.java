package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static final Map<String, Tile> tileMap = new HashMap<>();
    public static int TILE_WIDTH = 32;

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("door", new Tile(6, 10));
        tileMap.put("openDoor", new Tile(8, 10));
        tileMap.put("key", new Tile(18, 23));
        tileMap.put("ax", new Tile(9, 29));
        tileMap.put("bush", new Tile(3, 1));
        tileMap.put("water", new Tile(8, 5));
        tileMap.put("water<", new Tile(10, 5));
        tileMap.put("closedHole", new Tile(23, 27));
        tileMap.put("openedHole", new Tile(23, 26));
        tileMap.put("armor", new Tile(1, 23));
        tileMap.put("house", new Tile(0, 21));
        tileMap.put("manuscript", new Tile(17, 27));
        tileMap.put("wizard", new Tile(24, 2));
        tileMap.put("cabbage", new Tile(15, 6));
        tileMap.put("mushrooms", new Tile(5, 2));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

    public static class Tile {
        public final int x, y, w, h;

        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }
}
