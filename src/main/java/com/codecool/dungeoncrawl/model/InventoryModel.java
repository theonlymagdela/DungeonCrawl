package com.codecool.dungeoncrawl.model;

public class InventoryModel extends BaseModel {
    private final int gameStateId;
    private String itemName;
    private int inventoryId;


    public InventoryModel(String itemName, int gameStateId) {
        this.itemName = itemName;
        this.gameStateId = gameStateId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getGameStateId() {
        return gameStateId;
    }
}
