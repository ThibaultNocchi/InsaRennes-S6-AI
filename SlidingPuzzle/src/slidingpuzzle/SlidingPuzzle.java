package slidingpuzzle;

import java.util.ArrayList;

public class SlidingPuzzle {

    int[][] puzzle;
    int sideSize;
    int[] emptyIndex;

    public SlidingPuzzle(int sideSize){
        if(sideSize <= 0) throw new IllegalArgumentException("Side's size must be at least 1.");
        this.sideSize = sideSize;
        this.puzzle = new int[this.sideSize][this.sideSize];
        for(int i = 0; i < this.sideSize; ++i){
            for(int j = 0; j < this.sideSize; ++j){
                this.puzzle[i][j] = this.sideSize*i + (j+1);
            }
        }
        this.puzzle[this.sideSize-1][this.sideSize-1] = 0;
        this.emptyIndex = new int[]{this.sideSize-1, this.sideSize-1};
    }

    public SlidingPuzzle(int[][] puzzleCopy){

        this.sideSize = puzzleCopy.length;
        this.puzzle = new int[this.sideSize][this.sideSize];
        this.emptyIndex = new int[2];
        ArrayList<Integer> puzzleList = new ArrayList<>();

        for(int i = 0; i < this.sideSize; ++i){

            if(puzzleCopy[i].length != puzzleCopy.length) throw new IllegalArgumentException("Puzzle needs to be a square");
            for(int j = 0; j < this.sideSize; ++j){
                this.puzzle[i][j] = puzzleCopy[i][j];
                if(this.puzzle[i][j] == 0){
                    this.emptyIndex[0] = i;
                    this.emptyIndex[1] = j;
                }
                puzzleList.add(this.puzzle[i][j]);
            }

        }

        for(int i = 0; i < this.sideSize*this.sideSize; ++i) {
            if (!puzzleList.contains(i))
                throw new IllegalArgumentException("Number " + i + " is missing from the board.");
        }

    }

    public int[][] getPuzzle(){
        return this.puzzle;
    }
    public int getSideSize(){
        return this.sideSize;
    }
    public int[] getEmptyIndex(){
        return this.emptyIndex;
    }

    public void moveUp() throws IllegalMoveException{
        if(this.emptyIndex[0] == 0) throw new IllegalMoveException();
        this.puzzle[this.emptyIndex[0]][this.emptyIndex[1]] = this.puzzle[this.emptyIndex[0]-1][this.emptyIndex[1]];
        this.emptyIndex[0] -= 1;
        this.puzzle[this.emptyIndex[0]][this.emptyIndex[1]] = 0;
    }

    public void moveDown() throws IllegalMoveException{
        if(this.emptyIndex[0] == this.sideSize-1) throw new IllegalMoveException();
        this.puzzle[this.emptyIndex[0]][this.emptyIndex[1]] = this.puzzle[this.emptyIndex[0]+1][this.emptyIndex[1]];
        this.emptyIndex[0] += 1;
        this.puzzle[this.emptyIndex[0]][this.emptyIndex[1]] = 0;
    }

    public void moveLeft() throws IllegalMoveException{
        if(this.emptyIndex[1] == 0) throw new IllegalMoveException();
        this.puzzle[this.emptyIndex[0]][this.emptyIndex[1]] = this.puzzle[this.emptyIndex[0]][this.emptyIndex[1]-1];
        this.emptyIndex[1] -= 1;
        this.puzzle[this.emptyIndex[0]][this.emptyIndex[1]] = 0;
    }

    public void moveRight() throws IllegalMoveException{
        if(this.emptyIndex[1] == this.sideSize-1) throw new IllegalMoveException();
        this.puzzle[this.emptyIndex[0]][this.emptyIndex[1]] = this.puzzle[this.emptyIndex[0]][this.emptyIndex[1]+1];
        this.emptyIndex[1] += 1;
        this.puzzle[this.emptyIndex[0]][this.emptyIndex[1]] = 0;
    }

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

}
