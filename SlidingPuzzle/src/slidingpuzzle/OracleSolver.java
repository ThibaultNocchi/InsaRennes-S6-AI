package slidingpuzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class OracleSolver {

    private HashMap<SlidingPuzzle, Integer> oracle;

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

    public void solve(SlidingPuzzle slidingpuzzle){

        SlidingPuzzle current = slidingpuzzle;
        current.setLevel(this.getLevelOfPuzzle(current));

        while(current.getLevel() > 0){
            for(SlidingPuzzle move : current.getLegalMoves()){
                if(this.getLevelOfPuzzle(move) < current.getLevel()) current = move;
            }
            current.setLevel(this.getLevelOfPuzzle(current));
            System.out.println(current);
        }

    }

    public int getLevelOfPuzzle(SlidingPuzzle slidingPuzzle){
        return this.oracle.get(slidingPuzzle);
    }

}
