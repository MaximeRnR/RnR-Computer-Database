package com.excilys.formation.util;

import com.excilys.formation.ui.App;

public class ComputerDBException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -5131754047126066742L;

    public ComputerDBException() {
        super();
    }

    public ComputerDBException(String message) {
        super(message);
    }

    public ComputerDBException(String message, Throwable cause) {
        super(message, cause);
        System.out.println(message);
        App.menu();
    }

    public ComputerDBException(Throwable cause) {
        super(cause);
    }

}
