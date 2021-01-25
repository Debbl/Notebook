package run.aiwan;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import run.aiwan.ba03.SomeService;

public class MyTest03 {

    @Test
    public void test01() {
        String config = "ba03/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        SomeService proxy = (SomeService) ac.getBean("someService");
        String res = proxy.doOther("foo", 21);
        System.out.println(res);
    }
}
