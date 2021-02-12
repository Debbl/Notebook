package run.aiwan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping(value = "/doForward.do")
    public ModelAndView doSome() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "springMVC");
        mv.addObject("fun", "执行doSome方法");

        // 请求转发
        mv.setViewName("forward:/WEB-INF/view/show.jsp");
        return mv;
    }

    @RequestMapping(value = "doRedirect.do")
    public ModelAndView doWithRedirect(String name, Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("myname", name);
        mv.addObject("myage", age);

        // 重定向 不能访问/WEB-INF资源
        mv.setViewName("redirect:/hello.jsp");
        return mv;
    }
}
