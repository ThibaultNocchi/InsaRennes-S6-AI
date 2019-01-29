package slidingpuzzle;

import java.io.*;
import java.nio.channels.Channels;
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

    public void saveOracle(String path) throws IOException {

        File file = new File(path);
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        for(SlidingPuzzle sp : this.oracle){
            pw.println(sp.toLine());
        }
        pw.close();

    }

    public HashSet<SlidingPuzzle> getOracle(){
        return this.oracle;
    }

}
