package run.aiwan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.aiwan.domain.Student;
import run.aiwan.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MyController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/query")
    public Object doQueryStudents() {
        List<Student> students = this.studentService.queryStudentsService();
        return students;
    }
}
