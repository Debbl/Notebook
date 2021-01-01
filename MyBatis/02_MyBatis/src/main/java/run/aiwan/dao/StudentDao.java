package run.aiwan.dao;

import run.aiwan.domain.Student;

import java.util.List;

public interface StudentDao {
    // select
    List<Student> selectStudents();
    // insert
    int insertStudent(Student student);
    // update
    int updateStudent(Student student);
    // delete
    int deleteStudent(int studentId);
}
