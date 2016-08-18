package exception;

/**
 * Created by Daniel on 2016/8/18.
 */
public class NotInitialedExcepiton extends AbstractException {
    public NotInitialedExcepiton(String message) {
        setMessage(message);
    }

    @Override
    public int getErrorCode() {
        return 689;
    }
}
