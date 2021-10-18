package com.codecool.dungeoncrawl.logic.validate;

import com.codecool.dungeoncrawl.logic.CellType;

public class ValidMoveFactory {

    private static final CellType[] playerObstacles = new CellType[]{
            CellType.WALL,
            CellType.BUSH,
            CellType.WATER2,
            CellType.DOOR,
            CellType.HOUSE,
            CellType.CLOSEDHOLE,
            CellType.WIZARD
    };

    private static final CellType[] skeletonObstacles = new CellType[]{
            CellType.WALL,
            CellType.BUSH
    };
    private static final CellType[] contributorObstacles = new CellType[]{
            CellType.SKELETON
    };

    public static CellType[] getValidObstacle(String actorType) {

        switch (actorType) {
            case "player":
                return playerObstacles;
            case "skeleton":
                return skeletonObstacles;
            case "contributor":
                return contributorObstacles;
        }
        return null;
    }
}
