package slidingpuzzle;

import java.util.Objects;

/**
 * Implementation of puzzle as a long.
 * Side size is limited to 4 included, as from 5, the puzzle can't be stored on a long.
 */
public class SlidingPuzzleInt extends SlidingPuzzleAbstract<Long> implements SlidingPuzzle {

    /**
     * @see SlidingPuzzleAbstract#SlidingPuzzleAbstract(int)
     */
    public SlidingPuzzleInt(int sideSize){
        super(sideSize);
        if(sideSize > 4) throw new IllegalArgumentException("Side size needs to be max 4.");
    }

    /**
     * @see SlidingPuzzleAbstract#SlidingPuzzleAbstract(SlidingPuzzle)
     */
    public SlidingPuzzleInt(SlidingPuzzleInt slidingpuzzle){
        super(slidingpuzzle);
        this.puzzle = slidingpuzzle.getPuzzle();
    }

    /**
     * @see SlidingPuzzleAbstract#SlidingPuzzleAbstract(String)
     */
    public SlidingPuzzleInt(String line){
        super(line);
    }

    @Override
    public void initPuzzle(){
        this.puzzle = 0L;
    }

    @Override
    public int getValue(int x, int y){
        return (int)((this.puzzle >> (this.getAbsolutePosition(x,y)*4)) & 0xF);
    }

    @Override
    public void setValue(int v, int x, int y){
        super.setValue(v,x,y);
        long pos = this.getAbsolutePosition(x, y);
        this.puzzle = (this.puzzle & (~(0xFL<<(pos*4)))) | ((long)v << (pos*4));
    }

    @Override
    public SlidingPuzzleInt clone() {
        return new SlidingPuzzleInt(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlidingPuzzleInt that = (SlidingPuzzleInt) o;
        return this.getPuzzle().equals(that.getPuzzle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPuzzle());
    }

}
