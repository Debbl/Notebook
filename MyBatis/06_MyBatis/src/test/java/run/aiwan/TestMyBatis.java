package run.aiwan;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import run.aiwan.Utils.MyBatisUtils;
import run.aiwan.dao.StudentDao;
import run.aiwan.domain.Student;

import java.util.List;

public class TestMyBatis {
    @Test
    public void testSelectStudentIf() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setAge(18);

        PageHelper.startPage(1, 1);
        List<Student> studentList = dao.selectStudentIf(student);

        for (Student stu: studentList) {
            System.out.println("if===" + stu);
        }
    }
}
