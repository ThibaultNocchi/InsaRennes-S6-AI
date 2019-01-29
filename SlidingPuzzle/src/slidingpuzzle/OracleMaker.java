package slidingpuzzle;

import java.util.HashSet;
import java.util.LinkedList;

public class OracleMaker {

    private final SlidingPuzzle root;
    private LinkedList<SlidingPuzzle> toVisit;
    private HashSet<SlidingPuzzle> oracle;

    public OracleMaker(int sideSize){
        this.root = new SlidingPuzzle(sideSize);
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

            for(int[][] move : current.getLegalMoves()){
                SlidingPuzzle newSp = new SlidingPuzzle(move, current.getLevel()+1);
                if(!this.toVisit.contains(newSp) && !this.oracle.contains(newSp)) this.toVisit.add(newSp);
            }

            this.oracle.add(current);

        }

    }

    public HashSet<SlidingPuzzle> getOracle(){
        return this.oracle;
    }

}
