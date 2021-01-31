package run.aiwan.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import run.aiwan.domain.Student;
import run.aiwan.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String strId = req.getParameter("id");
        String strName = req.getParameter("name");
        String strEmail = req.getParameter("email");
        String strAge = req.getParameter("age");

        // Spring容器创建对象
        // String config = "applicationContext.xml";
        // ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        // System.out.println("容器的信息" + ac);
        // StudentService service = (StudentService) ac.getBean("studentService");

        // 监听器第一种方式
        //WebApplicationContext ac = null;
        //String key = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
        //Object attr = getServletContext().getAttribute(key);
        //if (attr != null) {
        //    ac = (WebApplicationContext) attr;
        //}
        //System.out.println("容器的信息" + ac);
        //StudentService service = (StudentService) ac.getBean("studentService");

        // 监听器第二种方式
        WebApplicationContext ac = null;
        ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        StudentService service = (StudentService) ac.getBean("studentService");
        System.out.println("容器信息" + ac);


        Student student = new Student();
        student.setId(Integer.valueOf(strId));
        student.setName(String.valueOf(strName));
        student.setEmail(String.valueOf(strEmail));
        student.setAge(Integer.valueOf(strAge));

        service.addStudent(student);

        // 处理结果
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
