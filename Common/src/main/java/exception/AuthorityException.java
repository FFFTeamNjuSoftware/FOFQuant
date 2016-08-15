package exception;

/**
 * Created by Daniel on 2016/8/15.
 */
public class AuthorityException extends AbstractException {

    public AuthorityException(String message) {
        setMessage(message);
    }

    @Override
    public int getErrorCode() {
        return 568;
    }
}
