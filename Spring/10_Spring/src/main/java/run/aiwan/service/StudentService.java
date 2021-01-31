package run.aiwan.service;

import run.aiwan.domain.Student;

import java.util.List;

public interface StudentService {

    int addStudent(Student student);

    List<Student> queryStudents();
}
