package slidingpuzzle;

import java.util.LinkedList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        OracleMaker oracle = new OracleMaker(new SlidingPuzzleInt(3));
        oracle.buildOracle();
        System.out.println();
        for(SlidingPuzzle sp : oracle.getOracle()){
            System.out.println(sp.getLevel());
            System.out.println(sp);
        }

    }
}
