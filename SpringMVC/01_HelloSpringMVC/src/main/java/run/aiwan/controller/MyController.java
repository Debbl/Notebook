package run.aiwan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 可以加value属性，默认是类名首字母小写
@Controller
public class MyController {
    // DispatcherServlet处理的请求会发送到这里，在web.xml定义的*.do
    // value的值可以有多个value = {"some.do", "other.do"}
    @RequestMapping(value = "/some.do")
    // 返回值是ModelAndView，数据处理和视图，这里将处理的数据发送的show.jsp
    public ModelAndView doSome() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "Message");
        mv.addObject("fun", "function");
        // 这里简写是因为在springmvc.xml指定了文件的前后缀
        mv.setViewName("show");
        return mv;
    }
}
