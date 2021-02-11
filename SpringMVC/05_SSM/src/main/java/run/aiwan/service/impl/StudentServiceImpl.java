package run.aiwan.service.impl;

import org.springframework.stereotype.Service;
import run.aiwan.dao.StudentDao;
import run.aiwan.domain.Student;
import run.aiwan.service.StudentService;

import javax.annotation.Resource;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    //引用类型自动注入@Autowired, @Resource
    @Resource
    private StudentDao studentDao;

    @Override
    public int addStudent(Student student) {
        int nums = this.studentDao.insertStudent(student);
        return nums;
    }

    @Override
    public List<Student> findStudents() {
        List<Student> students = this.studentDao.selectStudents();
        return students;
    }
}
