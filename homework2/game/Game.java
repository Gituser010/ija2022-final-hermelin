package ija.ija2022.homework2.game;

import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.common.Maze;
import ija.ija2022.homework2.common.MazeObject;

import java.util.ArrayList;
import java.util.List;

public class Game implements Maze {

    private int mainRows;
    private int mainCols;
    private Field[][] mainArrayF;

    public Game(Field[][] arrayF, int rows, int cols) {
        this.mainArrayF = arrayF;
        this.mainRows = rows;
        this.mainCols = cols;
    }
    public List<MazeObject> Ghosts;
    public List<MazeObject> Keys;

    public Game(int rows, int cols) {
        this.mainRows = rows;
        this.mainCols = cols;
        this.mainArrayF = new Field[rows+2][cols+2];
        this.Ghosts = new ArrayList<>();
        this.Keys = new ArrayList<>();
    }

    public Maze createGame(String[] maze, int rows, int cols) {
        rows += 2;
        cols += 2;

        Field[][] arrayF = new Field[rows][cols];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (x == 0 || y == 0 || x == rows - 1 || y == cols - 1) {
                    arrayF[x][y] = new WallField(x, y);
                } else {
                    if (maze[x - 1].charAt(y - 1) == 'X') {
                        arrayF[x][y] = new WallField(x, y);
                    } else {
                        arrayF[x][y] = new PathField(x, y);
                        if (maze[x - 1].charAt(y - 1) == 'S') {
                            PacmanObject P = new PacmanObject(arrayF[x][y]);
                            arrayF[x][y].put(P);
                        } else if (maze[x - 1].charAt(y - 1) == 'G') {
                            GhostObject G = new GhostObject(arrayF[x][y]);
                            arrayF[x][y].put(G);
                            this.Ghosts.add(this.ghosts().size(),G);
                        } else if (maze[x - 1].charAt(y - 1) == 'K') {
                            KeyObject K = new KeyObject(arrayF[x][y]);
                            arrayF[x][y].put(K);
                            this.Keys.add(this.keys().size(),K);
                        } else if (maze[x - 1].charAt(y - 1) == 'T') {
                            TargetObject T = new TargetObject(arrayF[x][y]);
                            arrayF[x][y].put(T);
                        }
                    }
                }
            }
        }
        Game GameMaze = new Game(arrayF, rows, cols);
        for (int x = 0; x < rows; x++)
            for (int y = 0; y < cols; y++)
                arrayF[x][y].setMaze(GameMaze);

        this.mainArrayF = arrayF;
        this.mainRows = rows;
        this.mainCols = cols;
        return GameMaze;
    }


    @Override
    public int numRows() {
        return this.mainRows;
    }

    @Override
    public int numCols() {
        return this.mainCols;
    }

    @Override
    public List<MazeObject> ghosts() {
        return new ArrayList<>(this.Ghosts);
    }

    @Override
    public List<MazeObject> keys() {
        return new ArrayList<>(this.Keys);
    }

    @Override
    public Field getField(int row, int col) {
        if (row >= this.numRows() || col >= this.numCols() || row < 0 || col < 0){
            return null;
        }
        Field[][] f = mainArrayF;
        return (Field) f[row][col];
    }


}
