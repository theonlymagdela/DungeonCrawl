package com.codecool.dungeoncrawl.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GameState extends BaseModel {
    private final List<String> discoveredMaps = new ArrayList<>();
    private String saveName;
    private Date savedAt;
    private String currentMap;
    private PlayerModel player;

    public GameState(String saveName,
                     String currentMap,
                     Date savedAt,
                     PlayerModel player) {
        this.saveName = saveName;
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

    public List<String> getDiscoveredMaps() {
        return discoveredMaps;
    }

    public void addDiscoveredMap(String map) {
        this.discoveredMaps.add(map);
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
}
