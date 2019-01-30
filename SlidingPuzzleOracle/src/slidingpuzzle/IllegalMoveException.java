package slidingpuzzle;

/**
 * Exception thrown when trying to move or get a move from a sliding puzzle if this move cannot be done.
 * Used to easily detect if a move worked or not.
 */
public class IllegalMoveException extends RuntimeException {

    public IllegalMoveException(){
        super();
    }

    public IllegalMoveException(String message){
        super(message);
    }

    public IllegalMoveException(Throwable cause){
        super(cause);
    }

    public IllegalMoveException(String message, Throwable cause){
        super(message,cause);
    }

}
