package slidingpuzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "oracle2x2.txt";

        OracleMaker oracle = new OracleMaker(new SlidingPuzzleInt(2));  // Creates an oracle with a goal being a 2x2 solution puzzle.
        oracle.buildOracle();   // Generates the oracle.
        oracle.saveOracle(filePath);    // Saves it to a file.

        OracleSolver oracleSolve = new OracleSolver(filePath);  // Loads an oracle from the file.
        ArrayList<SlidingPuzzle> solutions = oracleSolve.solve(new SlidingPuzzleInt("0 0 3 2 1"));  // Gets the solution of a puzzle 0 3 2 1. (First 0 is the level of the puzzle, which doesn't matter when solving it.)
        for(SlidingPuzzle solution : solutions){
            System.out.println(solution);   // Prints the path to the solution.
        }

    }
}
