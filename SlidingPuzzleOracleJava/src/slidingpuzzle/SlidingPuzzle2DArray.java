package slidingpuzzle;

import java.util.Arrays;

/**
 * Implementation of puzzle as a 2D array.
 */
public class SlidingPuzzle2DArray extends SlidingPuzzleAbstract<int[][]> implements SlidingPuzzle {

    /**
     * @see SlidingPuzzleAbstract#SlidingPuzzleAbstract(int)
     */
    public SlidingPuzzle2DArray(int sideSize){
        super(sideSize);
    }

    /**
     * @see SlidingPuzzleAbstract#SlidingPuzzleAbstract(SlidingPuzzle)
     */
    public SlidingPuzzle2DArray(SlidingPuzzle2DArray slidingpuzzle){
        super(slidingpuzzle);
        this.initPuzzle();

        final int[][] newPuzzle = new int[this.sideSize][];
        for(int i = 0; i < this.sideSize; ++i){
            newPuzzle[i] = Arrays.copyOf(slidingpuzzle.getPuzzle()[i], this.sideSize);
        }

        this.puzzle = newPuzzle;
    }

    /**
     * @see SlidingPuzzleAbstract#SlidingPuzzleAbstract(String)
     */
    public SlidingPuzzle2DArray(String line){
        super(line);
    }

    @Override
    public void initPuzzle(){
        this.puzzle = new int[this.sideSize][this.sideSize];
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
    public SlidingPuzzle2DArray clone(){
        return new SlidingPuzzle2DArray(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlidingPuzzle2DArray that = (SlidingPuzzle2DArray) o;
        return Arrays.deepEquals(puzzle, that.puzzle);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(puzzle);
    }
}
