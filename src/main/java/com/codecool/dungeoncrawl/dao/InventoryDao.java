package com.codecool.dungeoncrawl.dao;


import com.codecool.dungeoncrawl.model.InventoryModel;

import java.util.List;

public interface InventoryDao {
    void add(InventoryModel inventoryModel);

    void update(InventoryModel inventoryModel);

    void delete(InventoryModel inventoryModel);

    InventoryModel get(String id);

    List<InventoryModel> getAll();
}
