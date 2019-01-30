package slidingpuzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "oracle2x2.txt";

        OracleMaker oracle = new OracleMaker(new SlidingPuzzleInt(2));
        oracle.buildOracle();
        oracle.saveOracle(filePath);

        OracleSolver oracleSolve = new OracleSolver(filePath);
        ArrayList<SlidingPuzzle> solutions = oracleSolve.solve(new SlidingPuzzleInt("0 0 3 1 2"));
        for(SlidingPuzzle solution : solutions){
            System.out.println(solution);
        }

    }
}
