package run.aiwan.dao.impl;

import org.apache.ibatis.session.SqlSession;
import run.aiwan.dao.StudentDao;
import run.aiwan.domain.Student;
import run.aiwan.utils.MyBatisUtils;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> selectStudents() {
        // 获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        // sql语句
        String sqlId = "run.aiwan.dao.StudentDao.selectStudents";
        // 执行sql语句
        List<Student> students = sqlSession.selectList(sqlId);
        // 关闭
        sqlSession.close();
        return students;
    }

    @Override
    public int insertStudent(Student student) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        String sqlId = "run.aiwan.dao.StudentDao.insertStudent";
        int nums = sqlSession.insert(sqlId,student);
        // 修改需要提交事务
        sqlSession.commit();
        sqlSession.close();
        return nums;
    }

    @Override
    public int updateStudent(Student student) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        int nums = sqlSession.insert("run.aiwan.dao.StudentDao.updateStudent", student);
        sqlSession.commit();
        sqlSession.close();
        return nums;
    }

    @Override
    public int deleteStudent(int studentId) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        int nums = sqlSession.delete("run.aiwan.dao.StudentDao.deleteStudent", studentId);
        sqlSession.commit();
        sqlSession.close();
        return nums;
    }
}
