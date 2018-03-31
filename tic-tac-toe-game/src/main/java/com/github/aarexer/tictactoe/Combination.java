package com.github.aarexer.tictactoe;

public class Combination {
    private Tile[] tiles;

    Combination(Tile... tiles) {
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

    public Tile getTile(int index) {
        if (index < 0 || index > tiles.length) {
            throw new IllegalArgumentException(String.format("Can't get tile by index %d", index));
        }

        return tiles[index];
    }
}