package run.aiwan.exception;

// 用户年龄有问题是，抛出的异常
public class AgeException extends MyUserException {
    public AgeException() {
        super();
    }

    public AgeException(String message) {
        super(message);
    }
}
