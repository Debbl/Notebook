package run.aiwan;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import run.aiwan.ba06.SomeService;

public class MyTest06 {

    @Test
    public void test01() {
        String config = "ba06/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        SomeService proxy = (SomeService) ac.getBean("someService");
        proxy.doSome("foo", 21);
        System.out.println(proxy);
    }
}
