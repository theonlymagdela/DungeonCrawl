package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.Wizard;
import com.codecool.dungeoncrawl.logic.common.Properties;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapName, GameDatabaseManager dbManager) {

        InputStream is = MapLoader.class.getResourceAsStream(mapName);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'W':
                            cell.setType(CellType.BUSH);
                            break;
                        case '~':
                            cell.setType(CellType.WATER);
                            break;
                        case '<':
                            cell.setType(CellType.WATER2);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell, new Properties(5, 0, 0));
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            new Manuscript(cell, new Properties(0, 0, 0));
                            break;
                        case 'w':
                            cell.setType(CellType.WIZARD);
                            new Wizard(cell, new Properties(10, 1, 0.5f));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell, new Properties(10, 0, 0), dbManager.getNextPlayerId()));
                            break;
                        case 'D':
                            cell.setType(CellType.DOOR);
                            break;
                        case 'C':
                            cell.setType(CellType.CLOSEDHOLE);
                            break;
                        case 'H':
                            cell.setType(CellType.HOUSE);
                            break;
                        case 'K':
                            cell.setType(CellType.FLOOR);
                            new Key(cell, new Properties(0, 0, 0));
                            break;
                        case 'A':
                            cell.setType(CellType.FLOOR);
                            new Ax(cell, new Properties(0, 0.3f, 0));
                            break;
                        case 'R':
                            cell.setType(CellType.FLOOR);
                            new Armor(cell, new Properties(0, 0, 0.4f));
                            break;
                        case 'v':
                            cell.setType(CellType.CABBAGE);
                            new Cabbage(cell, new Properties(1, 0, 0));
                            break;
                        case 'M':
                            cell.setType(CellType.MUSHROOMS);
                            new Mushrooms(cell, new Properties(-1, 0, 0));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
