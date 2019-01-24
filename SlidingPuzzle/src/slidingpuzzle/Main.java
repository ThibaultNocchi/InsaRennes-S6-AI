package slidingpuzzle;

public class Main {

    public static void main(String[] args) {

        SlidingPuzzle sp = new SlidingPuzzle(3);
        System.out.println(sp);
        sp.moveUp();
        System.out.println(sp);
        sp.moveDown();
        System.out.println(sp);
        sp.moveLeft();
        System.out.println(sp);
        sp.moveRight();
        System.out.println(sp);

    }
}
