package run.aiwan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import run.aiwan.domain.Student;
import run.aiwan.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/addStudent.do")
    public ModelAndView addStudent(Student student) {
        ModelAndView mv = new ModelAndView();
        String tips = "注册失败";

        // 调用service处理student
        int nums = this.studentService.addStudent(student);
        if (nums > 0) {
            // 注册成功
            tips = "学生【" + student.getName() + "】注册成功";
            // 添加数据
            mv.addObject("tips", tips);
            // 返回结果页面
            mv.setViewName("result");
        } else {
            mv.addObject("msg", "注册失败");
            mv.setViewName("fail");
        }

        return mv;
    }

    @RequestMapping(value = "queryStudent.do")
    public @ResponseBody List<Student> queryStudent() {
        List<Student> students = this.studentService.findStudents();
        return students;
    }
}
