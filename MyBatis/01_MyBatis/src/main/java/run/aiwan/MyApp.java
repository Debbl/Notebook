package run.aiwan;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import run.aiwan.domain.Student;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyApp {
    public static void main(String[] args) throws IOException {
        String config = "mybatis.xml";
        InputStream in = Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession = factory.openSession();
        String sqlId = "run.aiwan.dao.StudentDao.selectStudents";
        List<Student> studentsList = sqlSession.selectList(sqlId);

        for(Student stu : studentsList){
            System.out.println("查询的学生="+stu);
        }
        sqlSession.close();

    }
}
