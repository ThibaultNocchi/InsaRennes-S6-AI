package slidingpuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingPuzzle {

    private int[][] puzzle;
    private final int sideSize;
    private int emptyX;
    private int emptyY;
    private int level;

    public SlidingPuzzle(int sideSize){
        if(sideSize <= 0) throw new IllegalArgumentException("Side size needs to be at least 1.");
        this.sideSize = sideSize;
        this.puzzle = new int[this.sideSize][this.sideSize];
        for(int i = 0; i < this.sideSize; ++i){
            for(int j = 0; j < this.sideSize; ++j){
                this.puzzle[i][j] = i*this.sideSize + j + 1;
            }
        }

        this.emptyX = this.sideSize-1;
        this.emptyY = this.sideSize-1;
        this.puzzle[this.emptyX][this.emptyY] = 0;

        this.level = 0;

    }

    public SlidingPuzzle(int[][] puzzle, int level){
        if(puzzle.length <= 0) throw  new IllegalArgumentException("Puzzle needs to be at least 1 unit wide.");
        this.sideSize = puzzle.length;
        this.puzzle = new int[this.sideSize][this.sideSize];
        ArrayList<Integer> addedValues = new ArrayList<>();
        for(int i = 0; i < this.sideSize; ++i){
            if(puzzle[i].length != this.sideSize) throw new IllegalArgumentException("Puzzle needs to be a square.");
            for(int j = 0; j < this.sideSize; ++j){
                if(addedValues.contains(puzzle[i][j]) || puzzle[i][j] < 0 || puzzle[i][j] >= this.sideSize*this.sideSize) throw new IllegalArgumentException("Illegal value in puzzle.");
                this.puzzle[i][j] = puzzle[i][j];
                addedValues.add(puzzle[i][j]);
                if(puzzle[i][j] == 0){
                    this.emptyX = i;
                    this.emptyY = j;
                }
            }
        }
        this.level = level;
    }

    public int[][] moveLeft(boolean edit){
        int[][] newPuzzle = this.puzzleCopy();
        if(this.emptyY > 0) this.swapCells(newPuzzle, this.emptyX, this.emptyY, this.emptyX, this.emptyY-1);
        else throw new IllegalMoveException();
        if(edit){
            this.puzzle = newPuzzle;
            --this.emptyY;
            ++this.level;
        }
        return newPuzzle;
    }

    public int[][] moveRight(boolean edit){
        int[][] newPuzzle = this.puzzleCopy();
        if(this.emptyY < this.sideSize-1) this.swapCells(newPuzzle, this.emptyX, this.emptyY, this.emptyX, this.emptyY+1);
        else throw new IllegalMoveException();
        if(edit){
            this.puzzle = newPuzzle;
            ++this.emptyY;
            ++this.level;
        }
        return newPuzzle;
    }

    public int[][] moveUp(boolean edit){
        int[][] newPuzzle = this.puzzleCopy();
        if(this.emptyX > 0) this.swapCells(newPuzzle, this.emptyX-1, this.emptyY, this.emptyX, this.emptyY);
        else throw new IllegalMoveException();
        if(edit){
            this.puzzle = newPuzzle;
            --this.emptyX;
            ++this.level;
        }
        return newPuzzle;
    }

    public int[][] moveDown(boolean edit){
        int[][] newPuzzle = this.puzzleCopy();
        if(this.emptyX < this.sideSize-1) this.swapCells(newPuzzle, this.emptyX+1, this.emptyY, this.emptyX, this.emptyY);
        else throw new IllegalMoveException();
        if(edit){
            this.puzzle = newPuzzle;
            ++this.emptyX;
            ++this.level;
        }
        return newPuzzle;
    }

    private int[][] puzzleCopy(){
        final int[][] newPuzzle = new int[this.sideSize][];
        for(int i = 0; i < this.sideSize; ++i){
            newPuzzle[i] = Arrays.copyOf(this.puzzle[i], this.sideSize);
        }
        return newPuzzle;
    }

    private void swapCells(int[][] puzzle, int cell1X, int cell1Y, int cell2X, int cell2Y){
        int buf = puzzle[cell1X][cell1Y];
        puzzle[cell1X][cell1Y] = puzzle[cell2X][cell2Y];
        puzzle[cell2X][cell2Y] = buf;
    }

    public int[][] getPuzzle(){
        return this.puzzle;
    }

    public int getSideSize(){
        return this.sideSize;
    }

    public int getLevel(){
        return this.level;
    }

    public List<int[][]> getLegalMoves(){
        List<int[][]> moves = new ArrayList<>();

        try{
            moves.add(this.moveUp(false));
        }catch(IllegalMoveException e){}

        try{
            moves.add(this.moveDown(false));
        }catch(IllegalMoveException e){}

        try{
            moves.add(this.moveLeft(false));
        }catch(IllegalMoveException e){}

        try{
            moves.add(this.moveRight(false));
        }catch(IllegalMoveException e){}

        return moves;
    }

    public boolean isSolution(){
        for(int i = 0; i < this.sideSize; ++i){
            for(int j = 0; j < this.sideSize; ++j){
                if(!(i == this.sideSize-1 && j == this.sideSize-1 && this.puzzle[i][j] == 0) && this.puzzle[i][j] != (i*this.sideSize + j + 1)) return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("");
        for(int i = 0; i < this.sideSize; ++i){
            for(int j = 0; j < this.sideSize; ++j){
                str.append(this.puzzle[i][j]).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlidingPuzzle that = (SlidingPuzzle) o;
        return Arrays.deepEquals(puzzle, that.puzzle);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(puzzle);
    }
}
