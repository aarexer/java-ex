package com.github.aarexer.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class MainApplication extends Application {
    private Pane root;
    private Tile[][] board = new Tile[Constants.Game.TILES_IN_COLUMN][Constants.Game.TILES_IN_ROW];
    private boolean isXPlayerTurn = true;
    private boolean isPlay = true;
    private List<Combination> combinations = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createRootLayout()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Parent createRootLayout() {
        root = new Pane();
        root.setPrefSize(Constants.MainWindow.WIDTH, Constants.MainWindow.HEIGHT);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * Constants.MainWindow.TILE_SIZE);
                tile.setTranslateY(i * Constants.MainWindow.TILE_SIZE);
                tile.setOnMouseClicked(event -> {
                    if (isPlay) {
                        if (event.getButton() == MouseButton.PRIMARY && tile.nonMarked()) {
                            if (isXPlayerTurn) {
                                tile.markAsX();
                                isXPlayerTurn = false;
                            } else {
                                tile.markAs0();
                                isXPlayerTurn = true;
                            }

                            tile.setMarked();
                        }
                        checkCombos();
                    }
                });

                board[i][j] = tile;

                root.getChildren().add(tile);
            }
        }

        initWinCombinations();

        return root;
    }

    private void initWinCombinations() {
        for (int i = 0; i < 3; i++) {
            combinations.add(new Combination(board[0][i], board[1][i], board[2][i]));
        }

        for (int i = 0; i < 3; i++) {
            combinations.add(new Combination(board[i][0], board[i][1], board[i][2]));
        }

        combinations.add(new Combination(board[0][0], board[1][1], board[2][2]));
        combinations.add(new Combination(board[2][0], board[1][1], board[0][2]));
    }

    private void checkCombos() {
        for(Combination combo : combinations) {
            if (combo.isWin()) {
                isPlay = false;
                congratulateWinner(combo);
                return;
            }
        }
    }

    private void congratulateWinner(Combination combo) {
        //for winner animation
        Line line = new Line();
        line.setStrokeWidth(15);
        line.setStroke(Color.RED);

        root.getChildren().add(line);
        line.setStartX(combo.tiles[0].getTileCentralX());
        line.setStartY(combo.tiles[0].getTileCentralY());

        line.setEndX(combo.tiles[0].getTileCentralX());
        line.setEndY(combo.tiles[0].getTileCentralY());

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
                new KeyValue(line.endXProperty(), combo.tiles[2].getTileCentralX()),
                new KeyValue(line.endYProperty(), combo.tiles[2].getTileCentralY())));

        tl.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class Combination {
        private Tile[] tiles;

        public Combination(Tile... tiles) {
            if (tiles.length == 3) {
                this.tiles = tiles;
            } else {
                throw new IllegalArgumentException("Too many arguments received!");
            }
        }

        public boolean isWin() {
            return tiles[0].isMarked()
                    && tiles[0].getValue().equals(tiles[1].getValue())
                    && tiles[0].getValue().equals(tiles[2].getValue());
        }
    }
}