package run.aiwan.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    // preHandle 预处理方法 返回值为Boolean 判断该请求是否处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("预处理方法执行！！！");
        return true;
    }

    // postHandle 后处理方法 处理器方法执行后执行 可以修改处理器的返回值包括ModelAndView
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 这里修改了返回数据
        modelAndView.addObject("name", "bar");
        modelAndView.setViewName("show");
    }

    // afterCompletion 最后执行的方法 只要preHandle执行该方法一定执行 做资源回收
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
