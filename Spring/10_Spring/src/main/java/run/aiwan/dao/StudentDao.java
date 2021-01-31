package run.aiwan.dao;

import run.aiwan.domain.Student;

import java.util.List;

public interface StudentDao {
    // 添加学生数据
    int insertStudent(Student student);

    // 查询学生数据
    List<Student> selectStudnets();
}
