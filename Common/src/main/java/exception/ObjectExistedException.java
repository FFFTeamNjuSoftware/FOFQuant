package exception;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 对象已存在
 */
public class ObjectExistedException extends AbstractException {

    public ObjectExistedException(String message) {
        setMessage(message);
    }

    @Override
    public int getErrorCode() {
        return 004;
    }

}
