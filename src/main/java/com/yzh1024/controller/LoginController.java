package com.yzh1024.controller;

import com.yzh1024.entity.Student;
import com.yzh1024.entity.Teacher;
import com.yzh1024.entity.User;
import com.yzh1024.service.StudentService;
import com.yzh1024.service.TeacherService;
import com.yzh1024.service.UserService;
import com.yzh1024.utils.MD5Utils;
import com.yzh1024.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/14
 **/

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String userName, String password, String type, String captcha, HttpSession session){

        //角色类型(1、2、3分别对应管理员、老师、学生)
        String type1 = "1";
        String type2 = "2";
        String type3 = "3";

        //判断是否为空
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)||StringUtils.isEmpty(type)||StringUtils.isEmpty(captcha)){
            return MapControl.getInstance().error("用户名或密码不能为空").getMap();
        }

        //验证码判断
        String captcha1 = (String) session.getAttribute("captcha");
        if(!captcha.equals(captcha1)){
            return MapControl.getInstance().error("验证码错误").getMap();
        }

        //管理员验证登录
        if(type1.equals(type)){
            User user = userService.login(userName, MD5Utils.getMD5(password));
            if(user!=null){
                session.setAttribute("user",user);
                session.setAttribute("type","1");
                return MapControl.getInstance().success().add("data",user).getMap();
            }else {
                return MapControl.getInstance().error("用户名或密码错误").getMap();
            }
        }

        //老师验证登录
        if(type2.equals(type)){
            Teacher teacher = teacherService.login(userName, MD5Utils.getMD5(password));
            if(teacher!=null){
                session.setAttribute("user",teacher);
                session.setAttribute("type","2");
                return MapControl.getInstance().success().add("data",teacher).getMap();
            }else {
                return MapControl.getInstance().error("用户名或密码错误").getMap();
            }
        }
        //学生验证登录
        if(type3.equals(type)){
            Student student = studentService.login(userName, MD5Utils.getMD5(password));
            if(student!=null){
                session.setAttribute("user",student);
                session.setAttribute("type","3");
                return MapControl.getInstance().success().add("data",student).getMap();
            }else {
                return MapControl.getInstance().error("用户名或密码错误").getMap();
            }
        }
        return MapControl.getInstance().error().getMap();
    }
}

















