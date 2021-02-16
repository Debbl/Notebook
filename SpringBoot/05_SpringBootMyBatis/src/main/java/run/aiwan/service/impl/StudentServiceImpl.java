package run.aiwan.service.impl;

import org.springframework.stereotype.Service;
import run.aiwan.dao.StudentDao;
import run.aiwan.domain.Student;
import run.aiwan.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public List<Student> queryStudentsService() {
        List<Student> students = this.studentDao.queryStudents();
        return students;
    }
}
