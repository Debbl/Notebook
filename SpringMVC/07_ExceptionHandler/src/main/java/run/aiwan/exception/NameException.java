package run.aiwan.exception;

// 用户姓名有问题时，抛出的异常
public class NameException extends MyUserException {
    public NameException() {
        super();
    }

    public NameException(String message) {
        super(message);
    }
}
