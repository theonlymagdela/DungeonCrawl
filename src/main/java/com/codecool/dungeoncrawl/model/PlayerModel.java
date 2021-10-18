package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.common.Properties;

public class PlayerModel extends BaseModel {
    private final String playerName;
    private Properties properties;
    private int x;
    private int y;

    public PlayerModel(String playerName, int x, int y) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
    }

    public PlayerModel(Player player) {
        super.id = player.getPlayerId();
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();
        this.properties = player.getProperties();

    }

    public String getPlayerName() {
        return playerName;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
