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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/14
 **/

@Controller
public class LogoutController {
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //是session里面的信息失效
        session.invalidate();
        //重定向到login
        return "redirect:login";
    }
}

















