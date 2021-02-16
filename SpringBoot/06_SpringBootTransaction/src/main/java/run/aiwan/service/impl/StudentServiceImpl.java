package run.aiwan.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.aiwan.dao.StudentDao;
import run.aiwan.domain.Student;
import run.aiwan.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentdao;

    @Override
    @Transactional
    public List<Student> queryStudentsService() {
        List<Student> students = this.studentdao.queryStudents();

        // 可以去掉注释，验证事务
//        int a = 10 / 0;

        return students;
    }
}
