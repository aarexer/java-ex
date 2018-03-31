package com.github.aarexer.tictactoe;

public final class Constants {
    private Constants() {
        throw new IllegalStateException("You shouldn't create instance of this class");
    }

    static final class MainWindow {
        private MainWindow() {
            throw new IllegalStateException("You shouldn't create instance of this class");
        }

        static final double WIDTH = 600;
        static final double HEIGHT = 600;
        static final double TILE_SIZE = 200;
    }

    static final class Game {
        private Game() {
            throw new IllegalStateException("You shouldn't create instance of this class");
        }

        static final int TILES_IN_ROW = 3;
        static final int TILES_IN_COLUMN = 3;
    }
}