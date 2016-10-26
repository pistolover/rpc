package rpc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

/**
*
* @author zhangwei_david
* @version $Id: MyTest.java, v 0.1 2014年12月31日 下午9:25:49 zhangwei_david Exp $
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:client.xml")
public class HelloServiceTest {
 @Autowired
 private RpcProxyFactory rpcProxy;
 @Test
 public void helloTest() {
   HelloService helloService = rpcProxy.create(HelloService.class);
   String result = helloService.hello();
   Assert.assertEquals("Hello! ", result);
   System.err.println(result);
 }
}
