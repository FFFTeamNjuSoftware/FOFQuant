package exception;

/**
 * Created by Daniel on 2016/9/3.
 */
public class NotAllowedException extends AbstractException {
    public NotAllowedException(String message) {
        setMessage(message);
    }

    @Override
    public int getErrorCode() {
        return 4545;
    }
}
