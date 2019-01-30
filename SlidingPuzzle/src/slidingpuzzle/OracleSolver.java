package slidingpuzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Solves a SlidingPuzzle with a previously generated oracle file.
 */
public class OracleSolver {

    /**
     * Parsed oracle with SlidingPuzzle as key and its level as value.
     */
    private HashMap<SlidingPuzzle, Integer> oracle;

    /**
     * Parses a file with the oracle locally.
     * @param path Path to the oracle.
     * @throws IOException
     */
    public OracleSolver(String path) throws IOException {

        this.oracle = new HashMap<>();

        File file = new File(path);

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                SlidingPuzzle sp = new SlidingPuzzleInt(line);
                oracle.put(sp, sp.getLevel());
            }
        }

    }

    /**
     * Gets the path to the solution of the oracle from the given puzzle.
     * @param slidingpuzzle Puzzle to start from.
     * @return List of puzzles to go through (effectively are moves) to reach the solution of the oracle.
     */
    public ArrayList<SlidingPuzzle> solve(SlidingPuzzle slidingpuzzle){

        SlidingPuzzle current = slidingpuzzle;
        current.setLevel(this.getLevelOfPuzzle(current));
        ArrayList<SlidingPuzzle> solutions = new ArrayList<>();
        solutions.add(current);

        while(current.getLevel() > 0){
            for(SlidingPuzzle move : current.getLegalMoves()){
                if(this.getLevelOfPuzzle(move) < current.getLevel()) current = move;
            }
            current.setLevel(this.getLevelOfPuzzle(current));
            solutions.add(current);
        }

        return solutions;

    }

    /**
     * Gets the level of a puzzle in the oracle.
     * @param slidingPuzzle Puzzle to get the level.
     * @return Level of the puzzle.
     */
    public int getLevelOfPuzzle(SlidingPuzzle slidingPuzzle){
        return this.oracle.get(slidingPuzzle);
    }

}
