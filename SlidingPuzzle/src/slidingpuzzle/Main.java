package slidingpuzzle;

public class Main {

    public static void main(String[] args) {

        int[][] puzzle = {{2,1,3},{4,6,5},{7,0,8}};
        SlidingPuzzle sp = new SlidingPuzzle(puzzle);
        sp.moveUp();
        System.out.println(sp);
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                System.out.print(puzzle[i][j]+" ");
            }
        }

    }
}
