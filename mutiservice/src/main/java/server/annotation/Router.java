package server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import server.HttpMethod;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Router {

    String value();

    HttpMethod method() default HttpMethod.GET;
}
