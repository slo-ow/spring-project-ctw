package kr.or.ctw.common.error;

import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value=500)
public class InvalidPasswordException extends RuntimeException {

	public InvalidPasswordException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidPasswordException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public InvalidPasswordException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public InvalidPasswordException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InvalidPasswordException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
