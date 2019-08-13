package by.sazanchuk.finalTask.dao;

/**
 * the type of dao exception
 */
public class DaoException extends Exception {
    /**
     * Instantiates a new dao exception
     */
    public DaoException() {}

    /**
     * Instantiates a new dao exception
     *
     * @param message message
     * @param cause cause
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new dao exception
     *
     * @param message message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Instantiates a new dao exception
     *
     * @param cause cause
     */
    public DaoException(Throwable cause) {
        super(cause);
    }
}