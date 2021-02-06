package run.aiwan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import run.aiwan.vo.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class MyController {
    @RequestMapping(value = "/some.do", method = RequestMethod.GET)
    public ModelAndView doSome(HttpServletRequest request,
                               HttpServletResponse response,
                               HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "message");
        mv.addObject("fun", "fun方法");
        mv.setViewName("show");
        return mv;
    }

    @RequestMapping(value = "/other.do", method = RequestMethod.POST)
    public ModelAndView doOther(@RequestParam(value = "rname") String name,
                                @RequestParam(value = "rage") Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", name);
        mv.addObject("age", age);
        mv.setViewName("show");
        return mv;
    }

    @RequestMapping(value = "/requestObject.do", method = RequestMethod.POST)
    public ModelAndView requestObject(Student student) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", student.getName());
        mv.addObject("age", student.getAge());
        mv.setViewName("show");
        return mv;
    }
}
