package slidingpuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SlidingPuzzleInt extends SlidingPuzzleAbstract<Long> implements SlidingPuzzle {

    public SlidingPuzzleInt(int sideSize){
        super(sideSize);
        if(sideSize > 4) throw new IllegalArgumentException("Side size needs to be max 4.");
    }

    public SlidingPuzzleInt(SlidingPuzzleInt slidingpuzzle){
        super(slidingpuzzle);
        this.puzzle = slidingpuzzle.getPuzzle();
    }

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
