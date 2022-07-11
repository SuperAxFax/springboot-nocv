package com.fax.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.http.HttpResponse;
import com.fax.entity.User;
import com.fax.service.UserService;
import com.fax.vo.DataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

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
        session.setAttribute("code",lineCaptcha.getCode());
        lineCaptcha.write(outputStream);
        outputStream.close();
    }
    //判断前端的用户名，密码是否正确，并进行登录
    /*
    * 1：接受从前端传递的用户名，密码，code值，以及session
    * 2：自定义一个查询用户名，密码的方法进行判断前端输入的用户名密码是否正确
    * 3：判断session中code的值与是否与前台的code值一样
    * 4：对判断后的结果进行返回
    * */
    @RequestMapping("/login/login")
    @ResponseBody
    public DataView login(String username, String password, String code, HttpSession session){
        User user = userService.check(username,password);
        String sessioncode = (String) session.getAttribute("code");
        DataView dataView = new DataView();
        if (sessioncode.equals(code)){
            dataView.setCode(200);
            dataView.setMsg("登录成功");
            session.setAttribute("user",user);
        }else{
            dataView.setCode(100);
            dataView.setMsg("登录失败");
        }
        return dataView;
    }
}
