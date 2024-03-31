package kg.tenir.thesis.exception;

public class DiplomaCreateException extends RuntimeException {
    public DiplomaCreateException(String message) {
        super(message);
    }

    public DiplomaCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
