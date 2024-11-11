package exceprions;

public class FileNotExistException extends RuntimeException {
    public FileNotExistException(String message) {
        super(message);

    }
}
