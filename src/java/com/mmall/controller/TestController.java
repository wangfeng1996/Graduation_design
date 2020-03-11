package com.mmall.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 验证项目的搭建是否成功了
 *
 *
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {
    @RequestMapping("hello")
    @ResponseBody
    public String Hello(){
        log.info("hello");
        return "hello, permission";
    }
}
