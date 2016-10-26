package rpc;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
*
* @author zhangwei_david
* @version $Id: Server.java, v 0.1 2014年12月31日 下午9:56:37 zhangwei_david Exp $
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class Server {
   @Test
   public void helloTest() throws InterruptedException {
       System.out.println("启动");
       TimeUnit.HOURS.sleep(5);
   }
}
