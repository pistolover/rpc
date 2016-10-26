package server.route;

import java.lang.reflect.Method;

public class RouteInfo {

    private String httpMethod;

    private String route;

    private Method handleMethod;

    public RouteInfo(String httpMethod, String route, Method handleMethod) {
        this.httpMethod = httpMethod;
        this.route = route;
        this.handleMethod = handleMethod;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Method getHandleMethod() {
        return handleMethod;
    }

    public void setHandleMethod(Method handleMethod) {
        this.handleMethod = handleMethod;
    }

    @Override
    public String toString() {
        return "RouteInfo{" + "httpMethod='" + httpMethod + '\'' + ", route='" + route + '\'' + ", handleMethod="
                + handleMethod + '}';
    }

}
