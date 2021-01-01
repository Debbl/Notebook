package run.aiwan;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import run.aiwan.dao.StudentDao;
import run.aiwan.dao.impl.StudentDaoImpl;
import run.aiwan.domain.Student;
import run.aiwan.utils.MyBatisUtils;

import java.util.List;

public class TestMyBatis {
    // 测试工具类MyBatisUtils
    @Test
    public void testUtils() {
        SqlSession session = MyBatisUtils.getSqlSession();
        List<Student> studentList = session.selectList("run.aiwan.dao.StudentDao.selectStudents");

        studentList.forEach(student -> System.out.println(student));

        session.close();
    }

    // 实现接口的查询测试
    @Test
    public void testSelect() {
        final List<Student> studentList = new StudentDaoImpl().selectStudents();
        studentList.forEach(student -> System.out.println(student));
    }

    // 实现接口的插入数据
    @Test
    public void testInsert() {
        StudentDao studentDao = new StudentDaoImpl();
        Student student = new Student();
        student.setId(1003);
        student.setName("ww");
        student.setAge(20);
        student.setEmail("ww@qq.com");
        int nums = studentDao.insertStudent(student);
        System.out.println("添加了个" + nums + "数据");
    }

    // 实现接口的更新数据
    @Test
    public void testUpdate() {
        StudentDao studentDao = new StudentDaoImpl();
        Student student = new Student();
        student.setId(1003);
        student.setAge(21);
        int nums = studentDao.updateStudent(student);
        System.out.println(nums);
    }

    // 实现接口的删除数据
    @Test
    public void testDelete() {
        StudentDao studentDao = new StudentDaoImpl();
        int nums = studentDao.deleteStudent(1002);
        System.out.println(nums);
    }
}
