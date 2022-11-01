package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Model {

    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    ;

    public Model() {
        resetGameTiles();
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
