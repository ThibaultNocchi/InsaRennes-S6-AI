package slidingpuzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {

//        OracleMaker oracle = new OracleMaker(new SlidingPuzzleInt(3));
//        oracle.buildOracle();
//        oracle.saveOracle("oracle3x3.txt");

        OracleSolver oracleSolve = new OracleSolver("oracle3x3.txt");
        ArrayList<SlidingPuzzle> solutions = oracleSolve.solve(new SlidingPuzzleInt("0 6 4 5 2 1 3 7 8 0"));
        for(SlidingPuzzle solution : solutions){
            System.out.println(solution);
        }

    }
}
