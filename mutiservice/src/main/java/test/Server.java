package test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Server implements ApplicationContextAware, InitializingBean{

    private Object clazz;
    public static void main(String[] args) {


    }
    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub
        
    }

}
