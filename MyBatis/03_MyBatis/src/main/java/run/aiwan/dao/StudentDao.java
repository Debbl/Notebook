package run.aiwan.dao;

import run.aiwan.domain.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectStudents();

    int insertStudent(Student student);
}
