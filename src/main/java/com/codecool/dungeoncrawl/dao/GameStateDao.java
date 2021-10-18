package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;

import java.util.List;

public interface GameStateDao {
    void add(GameState state);

    void add(GameState state, String savedName);

    void update(GameState state);

    GameState get(int id);

    List<GameState> getAll();

    boolean ifSaveNameExist(String saveName);

    int getPlayerId(String savedName);
}
