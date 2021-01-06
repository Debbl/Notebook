package run.aiwan;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import run.aiwan.dao.StudentDao;
import run.aiwan.domain.Student;
import run.aiwan.utils.MyBatisUtils;

import java.util.List;

public class TestMyBatis {
    /**
     * 使用mybatis的动态代理机制， 使用SqlSession.getMapper(dao接口 加class)
     * getMapper能获取dao接口对于的实现类对象。
     */
    @Test
    public void testSelectStudents() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        System.out.println("dao=" +  dao.getClass().getName());

        List<Student> students = dao.selectStudents();
        for (Student student: students) {
            System.out.println("学生:" + student);
        }
    }
}
