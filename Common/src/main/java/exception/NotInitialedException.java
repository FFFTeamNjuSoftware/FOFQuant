package exception;

/**
 * Created by Daniel on 2016/8/18.
 */
public class NotInitialedException extends AbstractException {
    public NotInitialedException(String message) {
        setMessage(message);
    }

    @Override
    public int getErrorCode() {
        return 689;
    }
}
