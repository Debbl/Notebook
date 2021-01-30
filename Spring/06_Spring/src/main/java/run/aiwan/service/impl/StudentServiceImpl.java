package run.aiwan.service.impl;

import run.aiwan.dao.StudentDao;
import run.aiwan.domain.Student;
import run.aiwan.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int addStudent(Student student) {
        int nums = this.studentDao.insertStudent(student);
        return nums;
    }

    @Override
    public List<Student> queryStudents() {
        List<Student> students = this.studentDao.selectStudents();
        return students;
    }
}
