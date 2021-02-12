package run.aiwan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import run.aiwan.exception.AgeException;
import run.aiwan.exception.MyUserException;
import run.aiwan.exception.NameException;

@Controller
public class MyController {

    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(String name, Integer age) throws MyUserException {

        ModelAndView mv = new ModelAndView();

        if (!"foo".equals(name)) {
            throw new NameException("姓名不正确！！！");
        }

        if (age == null || age > 80) {
            throw new AgeException("年龄比较大！！！");
        }

        mv.addObject("myName",name);
        mv.addObject("myAge",age);
        mv.setViewName("show");
        return mv;
    }
}
