package run.aiwan.dao;

import run.aiwan.domain.Student;

import java.util.List;

public interface StudentDao {
    // 插入数据
    int insertStudent(Student student);
    // 查询数据
    List<Student> selectStudents();
}
