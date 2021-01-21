package run.aiwan;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import run.aiwan.service.SomeService;
import run.aiwan.service.impl.SomeServiceImpl;

import java.util.Date;

public class SpringTest {
    // new 对象的方式
    @Test
    public void test01() {
        SomeServiceImpl service = new SomeServiceImpl();
        service.doSome();
    }

    @Test
    public void test02() {
        // 1. 配置文件
        String config = "applicationContext.xml";
        // 2. 创建表示spring容器的对象， ApplicationContext
        // ApplicationContext就是表示Spring容器，通过容器获取对象了
        // ClassPathXmlApplicationContext:表示从类路径中加载spring的配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        // 3. 从容器中获取某个对象， 你要调用的对象
        // getBean("配置文件中的bean的id值")
        SomeService service = (SomeService) ac.getBean("someService");
        service.doSome();
    }

    @Test
    public void test03() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Date myDate = (Date) ac.getBean("MyDate");
        System.out.println(myDate);
    }

    @Test
    public void test04() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        // 容器中对象的数量
        int nums = ac.getBeanDefinitionCount();
        System.out.println("容器中对象的数量 " + nums);

        // 容器中对象的名称
        String[] names = ac.getBeanDefinitionNames();
        for (String name: names) {
            System.out.println("容器中对象的名称 " + name);
        }
    }
}
