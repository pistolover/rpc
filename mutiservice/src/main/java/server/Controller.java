package server;

public interface Controller {

    /**
     * 请求前调用
     * @param context
     * @return
     */
    boolean preHandle(WebContext context);

    /**
     * 请后前调用
     * @param context
     */
    void postHandle(WebContext context);

    /**
     * 请求处理完毕后调用
     * @param context
     * @param e
     */
    void afterCompletion(WebContext context, Exception e);
}
