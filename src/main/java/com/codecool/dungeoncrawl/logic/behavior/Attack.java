package com.codecool.dungeoncrawl.logic.behavior;

import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Attack {
    public void goFight(Actor player, Actor enemy) {
        // actual scores.
        int playerScore = player.getProperties().getHealth();
        int enemyScore = enemy.getProperties().getHealth();

        // The fight properties.
        float playerDamage = player.getDamage();
        float enemyDamage = enemy.getDamage();

        // Sets health values after the fight.
        player.getProperties().setHealth((int) (playerScore - enemyDamage));
        enemy.getProperties().setHealth((int) (enemyScore - playerDamage));
    }
}
