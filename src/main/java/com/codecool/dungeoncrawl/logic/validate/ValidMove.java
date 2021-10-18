package com.codecool.dungeoncrawl.logic.validate;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class ValidMove {

    private final GameMap gameMap;

    public ValidMove(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public boolean isValidMove(String actorType, int x, int y) {
        Cell cellToCheck = gameMap.getCell(x, y);
        for (CellType cell : ValidMoveFactory.getValidObstacle(actorType)) {
            if (cellToCheck.getTileName().equals(cell.getTileName())) {
                return false;
            }
        }
        return true;
    }
}
