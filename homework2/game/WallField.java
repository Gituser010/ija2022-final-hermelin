package ija.ija2022.homework2.game;


import ija.ija2022.homework2.common.Field;
import ija.ija2022.homework2.common.Maze;
import ija.ija2022.homework2.common.MazeObject;

public class WallField implements Field {
    private final int mainRow;
    private final int mainCol;
    private Maze mazeMap;
    private MazeObject objectMaze;

    public WallField(int row, int col) {
        this.mazeMap = null;
        this.objectMaze = null;
        this.mainCol = col;
        this.mainRow = row;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WallField && ((WallField) obj).mainCol == this.mainCol && ((WallField) obj).mainRow == this.mainRow;
    }

    @Override
    public void setMaze(Maze maze) {
        this.mazeMap = maze;
    }

    @Override
    public Field nextField(Direction dirs) {
        if (!this.canMove()) {
            throw new UnsupportedOperationException("Cannot find next field from this field\n");
        }
        if (this.mazeMap == null) {
            return null;
        }
        return switch (dirs) {
            case D -> this.mazeMap.getField(this.mainRow + 1, this.mainCol);
            case L -> this.mazeMap.getField(this.mainRow, this.mainCol - 1);
            case R -> this.mazeMap.getField(this.mainRow, this.mainCol + 1);
            case U -> this.mazeMap.getField(this.mainRow - 1, this.mainCol);

        };
    }

    @Override
    public boolean put(MazeObject object) {
        if (!this.canMove()) {
            throw new UnsupportedOperationException("Cannot put object on this type of field\n");
        }
        if (this.isEmpty()) {
            this.objectMaze = object;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(MazeObject object) {
        if (!this.canMove()) {
            throw new UnsupportedOperationException("Cannot remove object from this type of field\n");
        }
        if (object != this.objectMaze) {
            return false;
        }
        this.objectMaze = null;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.objectMaze == null;
    }

    @Override
    public MazeObject get() {
        return this.objectMaze;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean contains(MazeObject MazeObject) {
        return false;
    }

    @Override
    public void addObserver(Observer var1) {

    }

    @Override
    public void removeObserver(Observer var1) {

    }

    @Override
    public void notifyObservers() {

    }
}
