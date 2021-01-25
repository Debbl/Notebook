package run.aiwan;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import run.aiwan.ba05.SomeService;

public class MyTest05 {

    @Test
    public void test01() {
        String config = "ba05/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        SomeService proxy = (SomeService) ac.getBean("someService");
        proxy.doSome("foo", 21);
        System.out.println(proxy);
    }
}
