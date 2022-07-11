package com.fax.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    //获取前端用户输入的验证码，与生成的验证码做对比
    @RequestMapping("/login/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //1：使用CaptchaUtil创建一个验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36, 4, 10);
        ServletOutputStream outputStream = response.getOutputStream();
        /*session.setAttribute("code",lineCaptcha.getCode());*/
        lineCaptcha.write(outputStream);
        outputStream.close();
    }
    //判断前端的用户名，密码是否正确

}
