package run.aiwan;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import run.aiwan.ba02.SomeService;

public class MyTest02 {

    @Test
    public void test01() {
        String config = "ba02/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        SomeService proxy = (SomeService) ac.getBean("someService");
        proxy.doOther("foo", 21);
        System.out.println(proxy);
    }
}
