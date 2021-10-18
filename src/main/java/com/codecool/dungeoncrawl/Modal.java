package com.codecool.dungeoncrawl;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;


public class Modal {
    private static final String GAME_TITLE = "Dungeon Crawl Game!";
    private static final String WELCOME_MSG = "Please enter Your character name:";
    private static final String SAVE_TITLE = "Saving game";
    private static final String SAVE_MSG = "Please enter the save name:";
    private static final String SAVE_BTN = "Save game";
    private static final String PLAY = "Let's play !";
    private static final String CANCEL = "Cancel";
    private static final TextField nameField = new TextField();
    private static final TextField saveNameField = new TextField();
    private static final TextField hintField = new TextField();
    private static String saveName;

    public static void display() {

        Stage window = getStage(GAME_TITLE, 600, 300);
        Label label = getLabel(WELCOME_MSG);
        Button closeBtn = getButton(PLAY);
        nameField.setMaxWidth(200);
        closeBtn.setDefaultButton(true);
        closeBtn.setOnAction(e -> window.close());
        VBox layout = getVbox(label, nameField, hintField, closeBtn);
        setScene(window, layout);
    }

    public static boolean saveDisplay() {

        TextInputDialog dialog = new TextInputDialog("Save name");
        dialog.setTitle(SAVE_TITLE);
        dialog.setHeaderText(null);
        dialog.setContentText(SAVE_MSG);
        dialog.setGraphic(null);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            saveName = result.get();
            return true;
        }
        return false;
    }

    public static String getSaveName() {
        return saveName;

    }

    private static Stage getStage(String title, Integer minWidth, Integer minHeight) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(minWidth);
        window.setMinHeight(minHeight);
        window.setResizable(false);
        window.getIcons().add(new Image(("/icon.jpg")));
        return window;
    }

    private static Label getLabel(String msg) {
        Label label = new Label();
        label.setText(msg);
        return label;
    }

    private static Button getButton(String btnLabel) {
        Button closeBtn = new Button(btnLabel);
        Image hintIcon = new Image("/hint.png");
        ImageView iconView = new ImageView(hintIcon);
        iconView.setFitHeight(20);
        iconView.setFitWidth(20);
        closeBtn.setGraphic(iconView);
        closeBtn.setOnMouseEntered(e -> {
            hintField.setVisible(true);
            hintField.setAlignment(Pos.CENTER);
            hintField.setText("If You know any of authors names You can play special special game!");
            hintField.setFont(Font.font("Dialog"));
            hintField.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        });
        closeBtn.setOnMouseExited(e -> {
            hintField.clear();
            hintField.setVisible(false);
        });
        return closeBtn;
    }

    private static VBox getVbox(Label label, TextField field, TextField hintField, Button closeBtn) {
        VBox layout = new VBox();
        final ImageView logoImage = new ImageView();
        Image logo = new Image("/icon.jpg");
        logoImage.setImage(logo);
        layout.getChildren().addAll(logoImage, label, nameField, closeBtn, hintField);
        hintField.setVisible(false);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        return layout;
    }

    private static VBox getSaveVbox(Label label, TextField field, Button saveBtn, Button closeBtn) {
        VBox saveLayout = new VBox();
        saveLayout.setSpacing(10);
        HBox btnLayout = new HBox();
        btnLayout.setSpacing(10);
        btnLayout.setAlignment(Pos.CENTER);
        btnLayout.getChildren().addAll(saveBtn, closeBtn);
        saveLayout.setMaxWidth(100);
        saveLayout.getChildren().addAll(label, saveNameField, btnLayout);
        saveLayout.setAlignment(Pos.CENTER);
        return saveLayout;
    }

    private static void setScene(Stage window, VBox layout) {
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    public static String getName() {
        return nameField.getText();
    }

    public static void message(String message, String message_title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(message_title);
        window.setMinWidth(400);
        window.setMinHeight(200);
        Label label = new Label();
        label.setText(message);
        Button closeMsgBox = new Button("Got it!");
        closeMsgBox.setDefaultButton(true);
        closeMsgBox.setOnAction(e -> window.close());
        VBox messageLayout = new VBox();
        messageLayout.getChildren().addAll(label, closeMsgBox);
        messageLayout.setSpacing(10);
        messageLayout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(messageLayout);
        window.setScene(scene);
        window.showAndWait();
    }

    public static boolean confirmOverwriteSave() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Save Confirmation");
        alert.setHeaderText("Would you like to overwrite the already existing state?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

}
