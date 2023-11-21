package belajar.springaop;

import belajar.springaop.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void testService() {
        Assertions.assertEquals("Hello Nidhom", helloService.hello("Nidhom"));
        Assertions.assertEquals("Hello Chusnun Nidhom", helloService.hello("Chusnun ", "Nidhom"));
        Assertions.assertEquals("Bye Nidhom", helloService.bye("Nidhom"));
        helloService.test();
    }
}
