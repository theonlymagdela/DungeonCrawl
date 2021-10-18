package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private final DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        add(player, 0);
    }

    @Override
    public void add(PlayerModel player, int player_id) {
        String sql;
        if (player_id == 0) {
            sql = "INSERT INTO player (player_name, hp, sp, dp, x, y) VALUES (?, ?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE player SET player_name = ?, hp = ?, sp = ?, dp = ?, x = ?, y = ? WHERE id = ?";
        }
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getProperties().getHealth());
            statement.setFloat(3, player.getProperties().getStrength());
            statement.setFloat(4, player.getProperties().getDefence());
            statement.setInt(5, player.getX());
            statement.setInt(6, player.getY());
            if (player_id != 0) {
                statement.setInt(7, player_id);
            }
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {

    }

    @Override
    public PlayerModel get(int id) {
        return null;
    }

    @Override
    public List<PlayerModel> getAll() {
        return null;
    }

    @Override
    public int getPlayerNextId() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT MAX(id) FROM player";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt(1) + 1; // next free Id in SQL db to add new player
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
