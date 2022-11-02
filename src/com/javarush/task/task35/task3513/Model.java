package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Model {

    private static final int FIELD_WIDTH = 4;
    public Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

    int score;
    int maxTile;

    public Model() {
        resetGameTiles();
    }

    private boolean compressTiles(Tile[] tiles) {
        int insertPosition = 0;
        boolean result = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (!tiles[i].isEmpty()) {
                if (i != insertPosition) {
                    tiles[insertPosition] = tiles[i];
                    tiles[i] = new Tile();
                    result = true;
                }
                insertPosition++;
            }
        }
        return result;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value && tiles[i].value != 0) {
                isChanged = true;
                int updatedValue = tiles[i].value * 2;

                if (maxTile < updatedValue) maxTile = updatedValue;
                score += updatedValue;
                tiles[i].value *= 2;
                tiles[i + 1].value = 0;
            }
        }
        compressTiles(tiles);
        return isChanged;
    }

    public void left() {
        boolean moveFlag = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                moveFlag = true;
            }
        }
        if (moveFlag) {
            addTile();
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = Arrays.stream(gameTiles)
                .flatMap(Arrays::stream)
                .filter(Tile::isEmpty)
                .collect(Collectors.toList());
        return emptyTiles;
    }

    public void addTile() {
        List<Tile> emptyTiles = this.getEmptyTiles();
        if (emptyTiles.isEmpty()) return;
        int randomTileNumber = (int) (Math.random() * emptyTiles.size());
        int randomTileValue = (Math.random() < 0.9) ? 2 : 4;
        Tile randomTile = emptyTiles.get(randomTileNumber);
        randomTile.value = randomTileValue;
    }

    public void resetGameTiles() {

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }
}
