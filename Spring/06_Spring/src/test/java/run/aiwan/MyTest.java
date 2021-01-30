package run.aiwan;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import run.aiwan.dao.StudentDao;
import run.aiwan.domain.Student;
import run.aiwan.service.StudentService;

import java.util.List;

public class MyTest {
    @Test
    public void test01() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        System.out.println("=================");
        String[] names =  ac.getBeanDefinitionNames();
        for (String name:names) {
            System.out.println(name);
        }
    }

    @Test
    public void testDaoInsert() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student student = new Student(1004, "foo", "foo@qq.com", 21);
        StudentDao studentDao = (StudentDao) ac.getBean("studentDao");
        int nums = studentDao.insertStudent(student);
        System.out.println(nums);
    }

    @Test
    public void testServiceInsert() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        StudentService studentService = (StudentService) ac.getBean("studentService");
        Student student = new Student(1005, "bar", "bar@qq.com", 23);
        int nums = studentService.addStudent(student);
        System.out.println(nums);
    }

    @Test
    public void testServiceSelect() {
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        StudentService studentService = (StudentService) ac.getBean("studentService");
        List<Student> students = studentService.queryStudents();
        for (Student student:students) {
            System.out.println(student);
        }
    }
}
