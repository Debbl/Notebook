package run.aiwan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import run.aiwan.vo.Student;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class MyController {
    @RequestMapping("some.do")
    public String doSome() {
        return "show";
    }

    @RequestMapping("myAjax.do")
    public void doMyAjax(Student student, HttpServletResponse response) throws IOException {
        student.setName("bar");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(student);

        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();

        pw.println(json);
        pw.flush();
        pw.close();
    }

    @RequestMapping("myAjax2.do")
    @ResponseBody
    public Student doMyAjax2(Student student) {
        student.setName("bar");
        return student;
    }
}
