package run.aiwan.dao;

import run.aiwan.domain.Student;

import java.util.List;

public interface StudentDao {
    public List<Student> selectStudents();
    public int insertStudent(Student student);
}
