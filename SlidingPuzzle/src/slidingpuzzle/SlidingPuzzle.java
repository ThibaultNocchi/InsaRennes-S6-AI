package slidingpuzzle;

import java.util.List;

public interface SlidingPuzzle {

    public SlidingPuzzle moveLeft(boolean edit);
    public SlidingPuzzle moveRight(boolean edit);
    public SlidingPuzzle moveUp(boolean edit);
    public SlidingPuzzle moveDown(boolean edit);
    public void swapCells(int cell1X, int cell1Y, int cell2X, int cell2Y);
    public void initPuzzle();

    public int getValue(int x, int y);
    public void setValue(int v, int x, int y);

    public int getLevel();
    public int getSideSize();
    public int getEmptyX();
    public int getEmptyY();

    public List<SlidingPuzzle> getLegalMoves();
    public long getAbsolutePosition(int x, int y);

    public boolean isSolution();

    public String toLine();

}
