package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.behavior.Move;
import com.codecool.dungeoncrawl.logic.common.Point;
import com.codecool.dungeoncrawl.logic.common.Properties;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {
    public Label playerNameLabel = new Label();
    // The game data.
    GameMap map;
    Inventory inventoryContainer = new Inventory();
    // The game logic.
    Move move;
    String actorType;
    GameDatabaseManager dbManager;
    // JavaFX controls.
    Canvas canvas;
    GraphicsContext context;
    Label healthLabel = new Label();
    Label strengthLabel = new Label();
    Label defenceLabel = new Label();
    Button pickUpButton = new Button("Pick Up");
    VBox inventory = new VBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Modal.display();
        playerNameLabel.setText(Modal.getName());
        //set up DB connection.
        setupDbManager();
        map = MapLoader.loadMap(GameMap.getMapName(0), dbManager);
        move = new Move(map, inventoryContainer);

        canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        context = canvas.getGraphicsContext2D();
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Player name: " + playerNameLabel.getText().toUpperCase()), 0, 0);


        ui.add(new Label("Health: "), 0, 1);
        ui.add(healthLabel, 1, 1);

        ui.add(new Label("Strength: "), 0, 2);
        ui.add(strengthLabel, 1, 2);

        ui.add(new Label("Defence: "), 0, 3);
        ui.add(defenceLabel, 1, 3);

        ui.add(new Label("Inventory: "), 0, 4);
        ui.add(inventory, 0, 5);

        ui.add(pickUpButton, 0, 20);

        setPickUpButtonActive(false);
        pickUpButton.setDefaultButton(true);
        pickUpButton.setOnAction(event -> {
            Cell cell = map.getCell(map.getPlayer().getX(), map.getPlayer().getY());
            Properties itemsProperties = cell.getItem().getProperties();
            inventoryContainer.addToInventory(cell.getItem());

            addProperties(itemsProperties);
            cell.setItem(null);
            setPickUpButtonActive(false);
            refreshInventory();
        });

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl Player : " + playerNameLabel.getText());
        primaryStage.show();

        actorType = nameValidation(playerNameLabel.getText());
        map.getPlayer().setName(playerNameLabel.getText());
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        KeyCombination saveCombinationMac = new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);
        KeyCombination saveCombinationWin = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);

        if (exitCombinationMac.match(keyEvent) ||
                exitCombinationWin.match(keyEvent) ||
                keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        } else if (saveCombinationMac.match(keyEvent) ||
                saveCombinationWin.match(keyEvent)) {
            boolean cancel;
            do {
                cancel = false;
                boolean saveDialogResult = Modal.saveDisplay();
                if (saveDialogResult) { // enter save name, return true if sth in;
                    if (dbManager.ifSaveNameExist(Modal.getSaveName())) {
                        if (Modal.confirmOverwriteSave()) { // confirm overwrite of save name
                            dbManager.updateGame(Modal.getSaveName(), map.getPlayer(), inventoryContainer.getItems());
                        } else {
                            cancel = true;
                        }
                    } else {
                        dbManager.saveGame(Modal.getSaveName(), map.getPlayer(), inventoryContainer.getItems());
                    }
                }
            } while (cancel);

        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        Point target = new Point();

        checkAndGetMovementCoordinates(keyEvent, target);

        if (isMoving(target)) {
            doMove(target);

            PickUpButtonActivity();
            obstacles();
            checkIfCanOpenDoor();
            refresh();
            refreshInventory();
        }
    }

    private void checkAndGetMovementCoordinates(KeyEvent keyEvent, Point target) {
        switch (keyEvent.getCode()) {
            case UP:
                target.setX(0);
                target.setY(-1);
                break;
            case DOWN:
                target.setX(0);
                target.setY(1);
                break;
            case LEFT:
                target.setX(-1);
                target.setY(0);
                break;
            case RIGHT:
                target.setX(1);
                target.setY(0);
        }
    }

    private void doMove(Point target) {
        Point current = new Point();
        current.setX(map.getPlayer().getX());
        current.setY(map.getPlayer().getY());

        move.go(current, target);
    }

    private void PickUpButtonActivity() {
        if (isItem()) {
            setPickUpButtonActive(true);
            canvas.setFocusTraversable(true);
        } else {
            setPickUpButtonActive(false);
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }

        Properties playerProperties = map.getPlayer().getProperties();
        healthLabel.setText("" + playerProperties.getHealth());
        strengthLabel.setText(getStringPercent(playerProperties.getStrength()));
        defenceLabel.setText(getStringPercent(playerProperties.getDefence()));
    }

    private void refreshInventory() {
        inventory.getChildren().removeAll(inventory.getChildren());
        for (Item item : inventoryContainer.getItems()) {
            inventory.getChildren().add(new Label(item.getTileName()));
        }
    }

    private void setPickUpButtonActive(boolean isActive) {
        pickUpButton.setDisable(!isActive);
        pickUpButton.setVisible(isActive);
    }

    private String getStringPercent(float percent) {
        return String.format("%.0f%%", (percent * 100));
    }

    public String nameValidation(String playerName) {
        playerName = playerName.toLowerCase();
        String[] contributorsNames = {"piotr", "magdalena"};
        for (String name : contributorsNames) if (playerName.equals(name)) return "contributor";
        return "player";
    }

    private boolean isItem() {
        return map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getItem() != null;
    }

    private boolean isMoving(Point target) {
        return target.getX() != 0 || target.getY() != 0;
    }

    private void checkIfCanOpenDoor() {

        int currentX = map.getPlayer().getX();
        int currentY = map.getPlayer().getY();

        Cell cellUp = map.getCell(currentX, currentY - 1);
        Cell cellDown = map.getCell(currentX, currentY + 1);
        Cell cellLeft = map.getCell(currentX - 1, currentY);
        Cell cellRight = map.getCell(currentX + 1, currentY);

        List<Cell> neighbourCells = Arrays.asList(cellUp, cellDown, cellLeft, cellRight);

        Properties playersProperties = map.getPlayer().getProperties();

        if (inventoryContainer.contains("key")) {
            if (isOutOfBoard(currentX, currentY))
                return;

            for (Cell cell : neighbourCells) {
                if (cell.getType() == CellType.DOOR) {
                    cell.setType(CellType.OPENDOOR);
                    inventoryContainer.removeItem("key");
                    map = MapLoader.loadMap(GameMap.getMapName(1), dbManager);
                } else if (cell.getType() == CellType.CLOSEDHOLE) {
                    cell.setType(CellType.OPENEDHOLE);
                    inventoryContainer.removeItem("key");
                    map = MapLoader.loadMap(GameMap.getMapName(2), dbManager);
                }
            }
        }
        if (inventoryContainer.contains("manuscript")) {
            if (isOutOfBoard(currentX, currentY))
                return;

            for (Cell cell : neighbourCells) {
                if (cell.getTileName().equals("wizard")) {
                    playersProperties.setStrength(playersProperties.getStrength() + 0.2f);
                    inventoryContainer.removeItem("manuscript");
                    refresh();
                    Modal.message(" Thank you my friend for this manuscript. I give you some additional strength in return as promised ", "Wizard");
                    break;
                }
            }
        } else {
            for (Cell cell : neighbourCells) {
                if (cell.getTileName().equals("wizard")) {
                    refresh();
                    Modal.message(" I'm looking for a rare manuscript my friend! I will exchange it for some additional strength if you find it ", "Wizard");
                }
            }
        }
    }

    private boolean isOutOfBoard(int currentX, int currentY) {
        return currentX == 0 || currentY == 0 || currentX == map.getWidth() - 1 || currentY == map.getHeight() - 1;
    }

    private void addProperties(Properties itemsProperties) {
        Properties playersProperties = map.getPlayer().getProperties();

        playersProperties.setDefence(playersProperties.getDefence() + itemsProperties.getDefence());
        playersProperties.setStrength(playersProperties.getStrength() + itemsProperties.getStrength());
        playersProperties.setHealth(playersProperties.getHealth() + itemsProperties.getHealth());
    }

    private void obstacles() {
        Cell cell = map.getCell(map.getPlayer().getX(), map.getPlayer().getY());

        if (cell.getType().equals(CellType.CABBAGE) || cell.getType().equals(CellType.MUSHROOMS)) {
            Properties obstProperties = cell.getObstacles().getProperties();
            addProperties(obstProperties);
        }
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }
}
