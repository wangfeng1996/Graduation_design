package com.mmall.common;


import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常的处理类
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {
    //这边需要引入一个包---pom.xml中的jsp-api
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getRequestURL().toString();
        ModelAndView mv;
        //只有抛出的异常是我们定义（exception包）的异常时候，才认为他的msg是直接给用户的
        //否则他的异常我们都使用默认异常来代替--system error
        String defaultMsg = "System error";


        // 这里我们要求项目中所有请求json数据，都使用.json结尾
        //对数据请求和页面请求进行判断---根据后缀名进行判断--.json是数据请求  .page结尾的是页面请求
        if (url.endsWith(".json")) {
            //如果当前的异常是我们定义的异常
            if (ex instanceof PermissionException || ex instanceof ParamException) {
                //这边使用的是异常里面的msg
                JsonData result = JsonData.fail(ex.getMessage());

                mv = new ModelAndView("jsonView", result.toMap());


            } else {

                log.error("unknown json exception, url:" + url, ex);
                //否则就是使用默认的异常---defaultMsg
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView", result.toMap());
            }
            //这边如果结尾是一.page结尾的，做成一个一界面返回的
        } else if (url.endsWith(".page")) { // 这里我们要求项目中所有请求page页面，都使用.page结尾
            log.error("unknown page exception, url:" + url, ex);

            JsonData result = JsonData.fail(defaultMsg);
            //寻找一个exception.jsp的页面
            mv = new ModelAndView("exception", result.toMap());
        } else {
            log.error("unknow exception, url:" + url, ex);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView", result.toMap());
        }

        return mv;
    }
}
