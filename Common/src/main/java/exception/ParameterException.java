package exception;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 参数错误
 */
public class ParameterException extends AbstractException {

    public ParameterException(String message) {
        setMessage(message);
    }

    @Override
    public int getErrorCode() {
        return 580;
    }
}
