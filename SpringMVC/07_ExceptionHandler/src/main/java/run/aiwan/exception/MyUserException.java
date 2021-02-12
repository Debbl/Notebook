package run.aiwan.exception;

// 用户异常
public class MyUserException extends Exception {
    public MyUserException() {
        super();
    }

    public MyUserException(String message) {
        super(message);
    }
}
