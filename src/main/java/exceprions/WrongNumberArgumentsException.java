package exceprions;

public class WrongNumberArgumentsException extends RuntimeException{
    public WrongNumberArgumentsException(String message) {
        super(message);
    }
}
