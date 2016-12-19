package com.github.aarexer.tictactoe;

public class Constants {
    private Constants() {
        throw new IllegalStateException("You shouldn't create instance of this class");
    }

    static class MainWindow {
        private MainWindow() {
            throw new IllegalStateException("You shouldn't create instance of this class");
        }

        static final double WIDTH = 600;
        static final double HEIGHT = 600;
        static final double TILE_SIZE = 200;
    }
}