package slidingpuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingPuzzleNaive extends SlidingPuzzleAbstract<int[][]> implements SlidingPuzzle {

    public SlidingPuzzleNaive(int sideSize){
        super(sideSize);
    }

    public SlidingPuzzleNaive(SlidingPuzzleNaive slidingpuzzle){
        super(slidingpuzzle);
        this.initPuzzle();

        final int[][] newPuzzle = new int[this.sideSize][];
        for(int i = 0; i < this.sideSize; ++i){
            newPuzzle[i] = Arrays.copyOf(slidingpuzzle.getPuzzle()[i], this.sideSize);
        }

        this.puzzle = newPuzzle;
    }

    @Override
    public void initPuzzle(){
        this.puzzle = this.puzzle = new int[this.sideSize][this.sideSize];
    }

    @Override
    public int getValue(int x, int y) {
        return this.puzzle[x][y];
    }

    @Override
    public void setValue(int v, int x, int y) {
        super.setValue(v,x,y);
        this.puzzle[x][y] = v;
    }

    @Override
    public SlidingPuzzleNaive clone(){
        SlidingPuzzleNaive sp = new SlidingPuzzleNaive(this);
        return sp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlidingPuzzleNaive that = (SlidingPuzzleNaive) o;
        return Arrays.deepEquals(puzzle, that.puzzle);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(puzzle);
    }
}
