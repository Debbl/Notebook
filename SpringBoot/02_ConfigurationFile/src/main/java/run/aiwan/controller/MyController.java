package run.aiwan.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import run.aiwan.domain.Student;

import javax.annotation.Resource;

@Controller
public class MyController {

    @Value("${student.name}")
    private String StudentName;

    @Value("${student.age}")
    private Integer StudentAge;

    @Resource
    private Student student;

    @RequestMapping("/hello")
    public @ResponseBody  Object doSome() {
        return this.student.getName() + this.student.getAge();
    }
}
