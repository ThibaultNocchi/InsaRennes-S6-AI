package slidingpuzzle;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {

        OracleMaker oracle = new OracleMaker(new SlidingPuzzleInt(3));
        oracle.buildOracle();
        oracle.saveOracle("");

        OracleSolver oracleSolve = new OracleSolver("");
        oracleSolve.solve(new SlidingPuzzleInt("0 6 4 5 2 1 3 7 8 0"));

    }
}
