package com.codecool.dungeoncrawl.logic;

class ActorTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);

//    @Test
//    void moveUpdatesCells() {
//        Player player = new Player(gameMap.getCell(1, 1), new Properties(10, 0, 0));
//        player.move(1, 0);
//
//        assertEquals(2, player.getX());
//        assertEquals(1, player.getY());
//        assertEquals(null, gameMap.getCell(1, 1).getActor());
//        assertEquals(player, gameMap.getCell(2, 1).getActor());
//    }

//    @Test
//    void cannotMoveIntoWall() {
//        gameMap.getCell(2, 1).setType(CellType.WALL);
//        Player player = new Player(gameMap.getCell(1, 1), new Properties(10, 0, 0));
//        player.move(1, 0);
//
//        assertEquals(1, player.getX());
//        assertEquals(1, player.getY());
//    }
//    @Disabled
//    @Test
//    void cannotMoveOutOfMap() {
//        Player player = new Player(gameMap.getCell(2, 1), new Properties(10, 0, 0));
//        player.move(1, 0);
//
//        assertEquals(2, player.getX());
//        assertEquals(1, player.getY());
//    }

//    @Test
//    void cannotMoveIntoAnotherActor() {
//        Player player = new Player(gameMap.getCell(1, 1), new Properties(10, 0, 0));
//        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1), new Properties(5, 0, 0));
//        player.move(1, 0);
//
//        assertEquals(1, player.getX());
//        assertEquals(1, player.getY());
//        assertEquals(2, skeleton.getX());
//        assertEquals(1, skeleton.getY());
//        assertEquals(skeleton, gameMap.getCell(2, 1).getActor());
//    }
}