package run.aiwan;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import run.aiwan.dao.StudentDao;
import run.aiwan.domain.Student;
import run.aiwan.utils.MyBatisUtils;

import java.util.ArrayList;
import java.util.List;

public class TestMyBatis {
    @Test
    public void testSelectStudentIf() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("zs");
        student.setAge(18);

        List<Student> students = dao.selectStudentIf(student);
        for (Student stu:students){
            System.out.println("if===" + stu);
        }
    }
}
