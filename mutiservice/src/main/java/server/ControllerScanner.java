package server;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import org.apache.log4j.Logger;

import server.annotation.BodyValue;
import server.annotation.Controller;
import server.annotation.ParamValue;
import server.annotation.PathValue;
import server.annotation.Router;
import server.route.RegRouteInfo;
import server.route.RouteInfo;
import server.route.RouteMatcher;
import server.util.ClassLoaderUtils;

public class ControllerScanner {

    private static final Logger log = Logger.getLogger("netty-server");

    private RouteMatcher routeMatcher;

    private ObjectFactory objectFactory;

    public ControllerScanner(RouteMatcher routeMatcher, ObjectFactory objectFactory) {
        this.routeMatcher = routeMatcher;
        this.objectFactory = objectFactory;
    }
    
    public static void main(String[] args) {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(7777);
        serverConfig.setContextPath("/mobile");
        RouteMatcher routeMatcher = new RouteMatcher(serverConfig.getContextPath());
        ControllerScanner scanner = new ControllerScanner(routeMatcher, serverConfig.getObjectFactory());
        scanner.scanControllers("xserver.api.module");
    }

    public void scanControllers(String packageName) {
        try {
            Set<Class<?>> classes = ClassLoaderUtils.getClasses(packageName);

            for (Class<?> clazz : classes) {

                if (!clazz.isAnnotationPresent(Controller.class)) {
                    continue;
                }

                objectFactory.instance(clazz.getCanonicalName(), true);

                for (Method method : clazz.getDeclaredMethods()) {
                    if (!Modifier.isPublic(method.getModifiers()) || !method.isAnnotationPresent(Router.class)) {
                        continue;
                    }
                    Router router = method.getAnnotation(Router.class);
                    if (HttpMethod.ALL == router.method()) {
                        for (HttpMethod httpMethod : HttpMethod.values()) {
                            RouteInfo routeInfo = routeMatcher.addRouter(router.value(), httpMethod.name(), method);
                            validate(routeInfo);
                            if (log.isDebugEnabled()) {
                                log.debug("Add Route:" + routeInfo);
                            }
                        }
                    } else {
                        RouteInfo routeInfo = routeMatcher.addRouter(router.value(), router.method().name(), method);
                        validate(routeInfo);
                        if (log.isDebugEnabled()) {
                            log.debug("Add Route:" + routeInfo);
                        }
                    }

                }
            }

        } catch (IOException e) {
            new WebException(e);
        } catch (ClassNotFoundException e) {
            new WebException(e);
        }

    }

    private void validate(RouteInfo routeInfo) {

        if (routeInfo instanceof RegRouteInfo) {
            RegRouteInfo regRouteInfo = (RegRouteInfo) routeInfo;

            // 验证参数annotation合法性
            Annotation[][] parameterAnnotations = regRouteInfo.getHandleMethod().getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Annotation[] annotations = parameterAnnotations[i];
                if (annotations.length == 1) {
                    if (annotations[0] instanceof PathValue) {
                        String key = ((PathValue) annotations[0]).value();
                        if (!regRouteInfo.getVarNames().contains(key)) {
                            throw new WebException(regRouteInfo.getRoute() + " does not contains " + key + " key.");
                        }
                    } else if (annotations[0] instanceof ParamValue) {
                        //
                    } else if (annotations[0] instanceof BodyValue) {
                        if (!regRouteInfo.getHandleMethod().getParameterTypes()[i].isAssignableFrom(byte[].class)) {
                            throw new WebException("The BodyValue annotation of " + regRouteInfo.getHandleMethod()
                                    + " must be place on byte[] type.");
                        }
                    }
                }
            }
        }
    }
}
