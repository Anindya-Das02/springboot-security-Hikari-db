package das.spring.security.app.models.exceptions;

public class EmptyBodyException extends Exception {
    public EmptyBodyException(String message){
        super(message);
    }
}
