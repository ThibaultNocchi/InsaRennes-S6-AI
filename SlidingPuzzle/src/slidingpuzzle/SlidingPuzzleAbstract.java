package slidingpuzzle;

import java.util.ArrayList;
import java.util.List;

public abstract class SlidingPuzzleAbstract<T> implements SlidingPuzzle {

    protected T puzzle;
    protected int sideSize;
    protected int sideSize2;
    protected int emptyX;
    protected int emptyY;
    protected int level;

    public SlidingPuzzleAbstract(int sideSize){

        if(sideSize <= 0) throw new IllegalArgumentException("Side size needs to be at least 1.");
        this.sideSize = sideSize;
        this.sideSize2 = this.sideSize*this.sideSize;
        this.level = 0;
        this.emptyX = this.sideSize-1;
        this.emptyY = this.sideSize-1;

        this.initPuzzle();

        for(int i = 0; i < this.sideSize; ++i){
            for(int j = 0; j < this.sideSize; ++j){
                if(i == this.sideSize-1 && j == this.sideSize-1) this.setValue(0, i, j);
                else this.setValue(i*this.sideSize+j+1, i, j);
            }
        }

    }

    public  SlidingPuzzleAbstract(SlidingPuzzle slidingpuzzle){
        this.sideSize = slidingpuzzle.getSideSize();
        this.sideSize2 = this.sideSize*this.sideSize;
        this.level = slidingpuzzle.getLevel();
        this.emptyX = slidingpuzzle.getEmptyX();
        this.emptyY = slidingpuzzle.getEmptyY();
    }

    @Override
    public SlidingPuzzle moveLeft(boolean edit){
        if(this.emptyY <= 0) throw new IllegalMoveException();
        if(edit){
            this.swapCells(this.emptyX, this.emptyY, this.emptyX, this.emptyY-1);
            --this.emptyY;
            ++this.level;
            return null;
        }else{
            SlidingPuzzle moved = this.clone();
            moved.moveLeft(true);
            return moved;
        }
    }

    @Override
    public SlidingPuzzle moveRight(boolean edit){
        if(this.emptyY >= this.sideSize-1) throw new IllegalMoveException();
        if(edit){
            this.swapCells(this.emptyX, this.emptyY, this.emptyX, this.emptyY+1);
            ++this.emptyY;
            ++this.level;
            return null;
        }else{
            SlidingPuzzle moved = this.clone();
            moved.moveRight(true);
            return moved;
        }
    }

    @Override
    public SlidingPuzzle moveUp(boolean edit){
        if(this.emptyX <= 0) throw new IllegalMoveException();
        if(edit){
            this.swapCells(this.emptyX, this.emptyY, this.emptyX-1, this.emptyY);
            --this.emptyX;
            ++this.level;
            return null;
        }else{
            SlidingPuzzle moved = this.clone();
            moved.moveUp(true);
            return moved;
        }
    }

    @Override
    public SlidingPuzzle moveDown(boolean edit){
        if(this.emptyX >= this.sideSize-1) throw new IllegalMoveException();
        if(edit){
            this.swapCells(this.emptyX, this.emptyY, this.emptyX+1, this.emptyY);
            ++this.emptyX;
            ++this.level;
            return null;
        }else{
            SlidingPuzzle moved = this.clone();
            moved.moveDown(true);
            return moved;
        }
    }

    @Override
    public void swapCells(int cell1X, int cell1Y, int cell2X, int cell2Y) {
        int buf = this.getValue(cell1X, cell1Y);
        this.setValue(this.getValue(cell2X, cell2Y), cell1X, cell1Y);
        this.setValue(buf, cell2X, cell2Y);
    }

    @Override
    public List<SlidingPuzzle> getLegalMoves(){
        List<SlidingPuzzle> moves = new ArrayList<>();
        try{
            moves.add(this.moveUp(false));
        }catch(IllegalMoveException ignored){}
        try{
            moves.add(this.moveDown(false));
        }catch(IllegalMoveException ignored){}
        try{
            moves.add(this.moveLeft(false));
        }catch(IllegalMoveException ignored){}
        try{
            moves.add(this.moveRight(false));
        }catch(IllegalMoveException ignored){}
        return moves;
    }

    @Override
    public long getAbsolutePosition(int x, int y){
        return x*this.sideSize + y;
    }

    @Override
    public int getSideSize(){
        return this.sideSize;
    }

    @Override
    public int getEmptyX(){
        return this.emptyX;
    }

    @Override
    public int getEmptyY(){
        return this.emptyY;
    }

    @Override
    public int getLevel(){
        return this.level;
    }

    public T getPuzzle(){
        return this.puzzle;
    }

    @Override
    public void setValue(int v, int x, int y){
        if(x >= this.sideSize || y >= this.sideSize || x < 0 || y < 0) throw new IllegalArgumentException("Index out of boundaries.");
        if(v >= this.sideSize2 || v < 0) throw new IllegalArgumentException("Value needs to be set max "+(this.sideSize2-1)+" and it was "+v+".");
    }

    @Override
    public boolean isSolution() {
        for(int i = 0; i < this.sideSize; ++i){
            for(int j = 0; j < this.sideSize; ++j){
                if(!(i == this.sideSize-1 && j == this.sideSize-1 && this.getValue(i,j) == 0) && this.getValue(i,j) != (i*this.sideSize + j + 1)) return false;
            }
        }
        return true;
    }

    @Override
    public String toLine(){
        StringBuilder str = new StringBuilder();
        str.append(this.getLevel());
        for(int i = 0; i < this.sideSize; ++i){
            for(int j = 0; j < this.sideSize; ++j){
                str.append(" ").append(this.getValue(i,j));
            }
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < this.sideSize; ++i){
            for(int j = 0; j < this.sideSize; ++j){
                str.append(this.getValue(i, j)).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public abstract SlidingPuzzle clone();

}
