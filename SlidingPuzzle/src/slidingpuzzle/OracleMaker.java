package slidingpuzzle;

import java.util.HashSet;
import java.util.LinkedList;

public class OracleMaker {

    private final SlidingPuzzle root;
    private LinkedList<SlidingPuzzle> toVisit;
    private HashSet<SlidingPuzzle> oracle;

    public OracleMaker(SlidingPuzzle root){
        this.root = root;
    }

    public void buildOracle(){

        this.toVisit = new LinkedList<>();
        this.oracle = new HashSet<>();
        this.toVisit.add(this.root);
        int currentLevel = this.root.getLevel();

        while(this.toVisit.size() > 0){

            SlidingPuzzle current = this.toVisit.poll();

            if(currentLevel != current.getLevel()){
                currentLevel = current.getLevel();
                System.out.println(currentLevel);
            }

            for(SlidingPuzzle move : current.getLegalMoves()){
                if(!this.toVisit.contains(move) && !this.oracle.contains(move)) this.toVisit.add(move);
            }

            this.oracle.add(current);

        }

    }

    public HashSet<SlidingPuzzle> getOracle(){
        return this.oracle;
    }

}
