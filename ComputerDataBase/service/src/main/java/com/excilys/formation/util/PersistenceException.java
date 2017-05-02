package com.excilys.formation.util;



public class PersistenceException extends RuntimeException {

    /**
     */
    private static final long serialVersionUID = 7610623136757332515L;

    /**
     */
    public PersistenceException() {
        super();
    }

    /**
     * @param message message
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * @param message message
     * @param cause cause
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause cause
     */
    public PersistenceException(Throwable cause) {
        super(cause);
    }

}
