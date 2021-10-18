package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface PlayerDao {
    void add(PlayerModel player);

    void add(PlayerModel player, int playerId);

    void update(PlayerModel player);

    PlayerModel get(int id);

    List<PlayerModel> getAll();

    int getPlayerNextId();
}
