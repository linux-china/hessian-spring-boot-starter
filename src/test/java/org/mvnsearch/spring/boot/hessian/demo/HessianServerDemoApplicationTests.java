package org.mvnsearch.spring.boot.hessian.demo;

import com.caucho.hessian.client.HessianProxyFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * hessian server tests
 *
 * @author linux_china
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HessianServerDemoApplicationTests {
    @Value("${local.server.port}")
    private Integer port;

    @Test
    public void testHessianClientInvoke() throws Exception {
        String url = "http://localhost:" + port + "/userService";
        HessianProxyFactory factory = new HessianProxyFactory();
        UserService basic = (UserService) factory.create(UserService.class, url);
        System.out.println("nick: " + basic.findNick(1));
    }
}
