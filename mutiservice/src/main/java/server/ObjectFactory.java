package server;

public interface ObjectFactory {

    Object instance(String className, boolean singleton);

}
