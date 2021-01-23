package run.aiwan;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import run.aiwan.ba06.Student;

public class MyTest06 {

    @Test
    public void test06() {
        String config = "ba06/total.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student student = (Student) ac.getBean("student");

        System.out.println(student);
    }
}
