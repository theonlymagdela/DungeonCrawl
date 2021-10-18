package com.codecool.dungeoncrawl.logic.behavior;

import com.codecool.dungeoncrawl.Modal;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.common.Point;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.validate.ValidMove;

public class Move {
    private final Inventory inventoryContainer;
    private final ValidMove moveValidation;
    private final Attack attack = new Attack();
    private final GameMap map;

    public Move(GameMap map, Inventory inventoryContainer) {
        this.map = map;
        this.moveValidation = new ValidMove(map);
        this.inventoryContainer = inventoryContainer;
    }

    public void go(Point currentPoint, Point direction) {
        int targetX = currentPoint.getX() + direction.getX();
        int targetY = currentPoint.getY() + direction.getY();

        if (moveValidation.isValidMove("player", targetX, targetY)) {
            Cell targetCell = map.getCell(targetX, targetY);
            Player player = map.getPlayer();
            Actor enemy = targetCell.getActor();

            checkEnemyAndFight(targetCell, player, enemy);
            checkIsGameOver(player);

            player.move(direction.getX(), direction.getY());
        }
    }

    private void checkEnemyAndFight(Cell targetCell, Player player, Actor enemy) {
        if (enemy != null) {
            attack.goFight(player, enemy);
            if (enemy.isDead())
                targetCell.setActor(null);
        }
    }

    private void checkIsGameOver(Player player) {
        if (player.isDead()) {
            Modal.message("Game over!", "Information");
        }
    }
}
