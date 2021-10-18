package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.common.Properties;

public class Player extends Actor {

    private String name;
    private int playerId;

    public Player(Cell cell, Properties properties, int playerId) {
        super(cell, properties);
        this.playerId = playerId;
    }

    @Override
    public String getTileName() {
        return "player";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
