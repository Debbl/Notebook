package run.aiwan;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import run.aiwan.ba08.SomeServiceImpl;

public class MyTest08 {

    @Test
    public void test01() {
        String config = "ba08/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        SomeServiceImpl proxy = (SomeServiceImpl) ac.getBean("someService");
        proxy.doSome("foo", 21);
        System.out.println(proxy.getClass().getName());
    }
}
