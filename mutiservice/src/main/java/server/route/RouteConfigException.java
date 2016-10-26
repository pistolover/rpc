package server.route;

public class RouteConfigException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 8011454239118791685L;

    public RouteConfigException() {
        super();
    }

    public RouteConfigException(String message) {
        super(message);
    }

    public RouteConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouteConfigException(Throwable cause) {
        super(cause);
    }

    protected RouteConfigException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
