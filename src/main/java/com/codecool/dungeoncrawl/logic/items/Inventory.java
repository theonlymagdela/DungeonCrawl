package com.codecool.dungeoncrawl.logic.items;

import java.util.LinkedList;

public class Inventory {

    LinkedList<Item> inventory = new LinkedList<>();

    public LinkedList<Item> getItems() {
        return inventory;
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public void removeItem(String name) {
        inventory.removeIf(item -> item.getTileName().equals(name));
    }

    public boolean contains(String name) {
        return inventory.stream().anyMatch(item -> item.getTileName().equals(name));
    }
}
