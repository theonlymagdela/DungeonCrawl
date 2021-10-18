package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private final DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState state) {
        add(state, null);
    }

    @Override
    public void add(GameState state, String savedName) {
        String sql;
        if (savedName == null) {
            sql = "INSERT INTO game_state (saved_name, current_map, saved_at_time, player_id) VALUES (?, ?, ?, ?)";
        } else {
            sql = "UPDATE game_state SET saved_name = ?, current_map = ?, saved_at_time = ? WHERE saved_name = ?";
        }
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getSaveName());
            statement.setString(2, state.getCurrentMap());
            statement.setDate(3, state.getSavedAt());
            if (savedName == null) { statement.setInt(4, state.getPlayer().getId()); }
            else { statement.setString(4, savedName); }
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean ifSaveNameExist(String savedName) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "select exists(select 1 from game_state where saved_name = ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, savedName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getPlayerId(String savedName) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT player_id FROM game_state WHERE saved_name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, savedName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(GameState state) {
    }

    @Override
    public GameState get(int id) {
        return null;
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }
}
