package rpc;

/**
*
* @author zhangwei_david
* @version $Id: HelloServiceImpl.java, v 0.1 2014年12月31日 下午9:28:02 zhangwei_david Exp $
*/
@RpcService(value = "helloService", inf = HelloService.class)
public class HelloServiceImpl implements HelloService {

   public String hello() {
       return "Hello! ";
   }
}
