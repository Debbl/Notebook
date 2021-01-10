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

    @Test
    public void testSelectStudentWhere() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("zs");

        List<Student> students = dao.selectStudentWhere(student);
        for (Student stu:students) {
            System.out.println("where===" + stu);
        }
    }

    @Test
    public void testSelectForeachOne() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Integer> idList = new ArrayList<>();
        idList.add(1001);
        idList.add(1002);

        List<Student> students =  dao.selectForeachOne(idList);

        for (Student stu:students) {
            System.out.println("foreachOne===" + stu);
        }
    }

    @Test
    public void testSelectForeachTwo() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student s1 = new Student();
        Student s2 = new Student();

        s1.setId(1001);
        s2.setId(1002);

        List<Student> studentList = new ArrayList();
        studentList.add(s1);
        studentList.add(s2);

        List<Student> students = dao.selectForeachTwo(studentList);

        for (Student stu: students) {
            System.out.println("foreachTwo===" + stu);
        }
    }

    @Test
    public void testSelectSqlFragment() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student s1 = new Student();
        Student s2 = new Student();

        s1.setId(1001);
        s2.setId(1002);

        List<Student> studentList = new ArrayList();
        studentList.add(s1);
        studentList.add(s2);

        List<Student> students =  dao.selectStudentSqlFragment(studentList);

        for (Student stu: students) {
            System.out.println("sql===" + stu);
        }
    }

}
