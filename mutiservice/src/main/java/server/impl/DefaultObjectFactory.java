package server.impl;

import java.util.concurrent.ConcurrentHashMap;

import server.ObjectFactory;
import server.util.ClassUtils;

public class DefaultObjectFactory implements ObjectFactory {

    private ConcurrentHashMap<String, Object> objectCache = new ConcurrentHashMap<String, Object>();

    public Object instance(String className, boolean singleton) {
        if (singleton) {
            return getFromObjectCache(className);
        }

        return null;
    }

    private Object getFromObjectCache(String className) {
        Object o = objectCache.get(className);

        if (o != null) {
            return o;
        }

        o = ClassUtils.newInstance(className);

        Object oldObject = objectCache.putIfAbsent(className, o);

        if (oldObject != null) {
            o = oldObject;
        }

        return o;
    }

}
