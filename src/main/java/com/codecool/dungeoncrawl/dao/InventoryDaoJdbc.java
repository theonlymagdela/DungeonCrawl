package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class InventoryDaoJdbc implements InventoryDao {
    private final DataSource dataSource;

    public InventoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(InventoryModel inventoryModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO inventory (game_state_id, item_name) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, inventoryModel.getGameStateId());
            statement.setString(2, inventoryModel.getItemName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            inventoryModel.setInventoryId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(InventoryModel inventoryModel) {
    }

    @Override
    public void delete(InventoryModel inventoryModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM inventory WHERE game_state_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, inventoryModel.getGameStateId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InventoryModel get(String id) {
        return null;
    }

    @Override
    public List<InventoryModel> getAll() {
        return null;
    }
}
