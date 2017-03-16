package com.excilys.util.java;

import com.excilys.ui.java.App;

public class ComputerDBException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5131754047126066742L;

	public ComputerDBException() { super(); }
	public ComputerDBException(String message) { super(message); }
	public ComputerDBException(String message, Throwable cause) { super(message, cause);
	System.out.println(message);
	App.menu(); }
	public ComputerDBException(Throwable cause) { super(cause); }

}
