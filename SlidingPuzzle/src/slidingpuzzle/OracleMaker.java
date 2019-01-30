package slidingpuzzle;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Builds an oracle for a given implementation of a SlidingPuzzle and a given root.
 * The oracle will find the number of moves required to get to any position from the root position.
 * Usually we give it the solution, so that we can deduce the number of moves required from any random set to solve it.
 */
public class OracleMaker {

    /**
     * Root puzzle.
     */
    private final SlidingPuzzle root;

    /**
     * Oracle with the different SlidingPuzzle and their distance (SlidingPuzzle.level) from the root.
     */
    private HashSet<SlidingPuzzle> oracle;

    /**
     * Saves the root.
     * @param root Root puzzle.
     */
    public OracleMaker(SlidingPuzzle root){
        this.root = root;
        this.oracle = new HashSet<>();
    }

    /**
     * Builds the oracle from the root.
     * It follows a breadth first search algorithm.
     * From the root, we first get all its different moves possible and add them to a queue.
     * The root is then added to the oracle.
     * We do it over again for every puzzle we can retrieve from the queue.
     */
    public void buildOracle(){

        LinkedList<SlidingPuzzle> toVisit = new LinkedList<>();
        this.oracle.clear();
        toVisit.add(this.root);
        int currentLevel = this.root.getLevel();

        while(toVisit.size() > 0){

            SlidingPuzzle current = toVisit.poll();

            if(currentLevel != current.getLevel()){     // Outputs the new level of search when we change it.
                currentLevel = current.getLevel();
                System.out.println(currentLevel);
            }

            for(SlidingPuzzle move : current.getLegalMoves()){
                if(!toVisit.contains(move) && !this.oracle.contains(move)) toVisit.add(move);
            }

            this.oracle.add(current);

        }

    }

    /**
     * Saves a previously calculated oracle to a file.
     * @param path Path to the file.
     * @throws IOException
     */
    public void saveOracle(String path) throws IOException {

        File file = new File(path);
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        for(SlidingPuzzle sp : this.oracle){
            pw.println(sp.toLine());
        }
        pw.close();

    }

    /**
     * Gets the oracle.
     * @return The oracle.
     */
    public HashSet<SlidingPuzzle> getOracle(){
        return this.oracle;
    }

}
