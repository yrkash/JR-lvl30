package com.javarush.task.task35.task3513;

public class Main {
    public static void main(String[] args) {
        Model m = new Model();
        m.gameTiles = new Tile[][]{{new Tile(4), new Tile(4), new Tile(2), new Tile(0)},
                {new Tile(4), new Tile(2), new Tile(0), new Tile(4)},
                {new Tile(4), new Tile(4), new Tile(4), new Tile(0)},
                {new Tile(4), new Tile(4), new Tile(4), new Tile(4)}};

        printGame(m.gameTiles);
        m.up();
//        m.rotation();
        System.out.println();
        printGame(m.gameTiles);

    }
    public static void printGame(Tile[][] gameTiles) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(gameTiles[i][j].value + " ");
            }
            System.out.println();
        }
    }
}
