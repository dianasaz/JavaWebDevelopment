package exceptions;

public class NoSuchFileException extends Exception{
    public NoSuchFileException(String message, Exception exception){
        super(message, exception);
    }

    public NoSuchFileException(String message){
        super(message);
    }

    public NoSuchFileException(Exception exception){
        super(exception);
    }
}
