package exception;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 对象未找到
 */
public class ObjectNotFoundException extends AbstractException {

    public ObjectNotFoundException(String message) {
        setMessage(message);
    }

    @Override
    public int getErrorCode() {
        return 005;
    }
}
