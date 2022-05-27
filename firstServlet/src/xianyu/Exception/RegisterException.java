// 处理注册异常，主要是通过继承基类Exceptions实现的
package xianyu.Exception;

public class RegisterException extends Exception{
	private static final long serialVersionUID = 1L;

	public RegisterException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RegisterException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RegisterException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
