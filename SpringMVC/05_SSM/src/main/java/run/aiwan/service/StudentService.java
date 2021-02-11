package run.aiwan.service;

import run.aiwan.domain.Student;

import java.util.List;

public interface StudentService {

    // 添加学生
    int addStudent(Student student);
    // 查询所有的学生
    List<Student> findStudents();
}
