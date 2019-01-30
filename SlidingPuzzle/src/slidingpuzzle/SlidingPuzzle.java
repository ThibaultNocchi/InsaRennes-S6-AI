package slidingpuzzle;

import java.util.List;

/**
 * Interface used to represent a sliding puzzle.
 */
public interface SlidingPuzzle {

    /**
     * Tries to move the empty space to the left.
     * If it succeeds and edit is true, the puzzle object which this method was called on will be edited.
     * If it succeeds and edit is false, a new SlidingPuzzle with the moved space will be returned.
     * @param edit Whether the object's puzzle needs to be edited or returned as a new SlidingPuzzle.
     * @return Freshly moved SlidingPuzzle or null.
     */
    public SlidingPuzzle moveLeft(boolean edit);

    /**
     * Tries to move the empty space to the right.
     * If it succeeds and edit is true, the puzzle object which this method was called on will be edited.
     * If it succeeds and edit is false, a new SlidingPuzzle with the moved space will be returned.
     * @param edit Whether the object's puzzle needs to be edited or returned as a new SlidingPuzzle.
     * @return Freshly moved SlidingPuzzle or null.
     */
    public SlidingPuzzle moveRight(boolean edit);

    /**
     * Tries to move the empty space upwards.
     * If it succeeds and edit is true, the puzzle object which this method was called on will be edited.
     * If it succeeds and edit is false, a new SlidingPuzzle with the moved space will be returned.
     * @param edit Whether the object's puzzle needs to be edited or returned as a new SlidingPuzzle.
     * @return Freshly moved SlidingPuzzle or null.
     */
    public SlidingPuzzle moveUp(boolean edit);

    /**
     * Tries to move the empty space downwards.
     * If it succeeds and edit is true, the puzzle object which this method was called on will be edited.
     * If it succeeds and edit is false, a new SlidingPuzzle with the moved space will be returned.
     * @param edit Whether the object's puzzle needs to be edited or returned as a new SlidingPuzzle.
     * @return Freshly moved SlidingPuzzle or null.
     */
    public SlidingPuzzle moveDown(boolean edit);

    /**
     * Swaps two cells in the puzzle without checking the legality of the move.
     * @param cell1X Line of the first cell.
     * @param cell1Y Row of the first cell.
     * @param cell2X Line of the first cell.
     * @param cell2Y Row of the first cell.
     */
    public void swapCells(int cell1X, int cell1Y, int cell2X, int cell2Y);

    /**
     * Initialises the puzzle of the SlidingPuzzle object.
     */
    public void initPuzzle();

    /**
     * Gets the value at coordinates (X,Y) in the puzzle.
     * @param x Line of the cell.
     * @param y Row of the cell.
     * @return Value at (X,Y).
     */
    public int getValue(int x, int y);

    /**
     * Sets the value at coordinates (X,Y) in the puzzle.
     * @param v Value to put in the cell.
     * @param x Line of the cell.
     * @param y Row of the cell.
     */
    public void setValue(int v, int x, int y);

    /**
     * Gets the level of the puzzle.
     * @return Level of the puzzle.
     */
    public int getLevel();

    /**
     * Gets the side size of the puzzle.
     * @return Side size of the puzzle.
     */
    public int getSideSize();

    /**
     * Gets the line number of the empty space.
     * @return Line number of the empty space.
     */
    public int getEmptyX();

    /**
     * Gets the row number of the empty space.
     * @return Row number of the empty space.
     */
    public int getEmptyY();

    /**
     * Sets the level of the puzzle.
     * @param level Level of the puzzle.
     */
    public void setLevel(int level);

    /**
     * Gets a list of SlidingPuzzle which are only moved once from the current object's puzzle.
     * @return List of SlidingPuzzle moved by 1.
     */
    public List<SlidingPuzzle> getLegalMoves();

    /**
     * Gets the absolute position of a cell as if the puzzle was represented in a line.
     * If we have a 2x2 puzzle like this:
     * 1 2
     * 3 0
     * The value 3 is at X = 1, Y = 0 and its absolute position is 2.
     * @param x Line of the cell.
     * @param y Row of the cell.
     * @return Absolute position of the cell.
     */
    public long getAbsolutePosition(int x, int y);

    /**
     * Whether the current puzzle is the solution or not.
     * @return Solution or not.
     */
    public boolean isSolution();

    /**
     * Gets the current puzzle as a line (to export into a file for example).
     * @return Line representing the puzzle and its level.
     */
    public String toLine();

}
