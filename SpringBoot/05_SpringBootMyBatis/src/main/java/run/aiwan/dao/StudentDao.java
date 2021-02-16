package run.aiwan.dao;

import org.apache.ibatis.annotations.Mapper;
import run.aiwan.domain.Student;

import java.util.List;

//@Mapper
public interface StudentDao {
    // 查询学生
    List<Student> queryStudents();
}
