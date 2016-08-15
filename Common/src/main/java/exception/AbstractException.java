package exception;

/**
 * Created by Daniel on 2016/8/15.
 */
public abstract class AbstractException extends Exception {

    private String message;

    /**
     * 获得错误码
     *
     * @return
     */
    public abstract int getErrorCode();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
