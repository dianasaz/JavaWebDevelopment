package exceptions;

public class InvalidDataException extends Exception {
    public InvalidDataException(String message, Exception ex){
        super(message, ex);
    }

    public InvalidDataException(String message){
        super(message);
    }
}
