package az.iktlab.securityservice.exception;

public class WrongFieldException extends RuntimeException{
    public WrongFieldException() {
        super();
    }

    public WrongFieldException(String message) {
        super(message);
    }
}
