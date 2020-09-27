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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/14
 **/

@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/info")
    public String info(){
        return "info";
    }

    @GetMapping("/pwd")
    public String pwd(){
        return "pwd";
    }


    /**
     * 修改密码
     * @param sourcePwd
     * @param newPwd
     * @param type
     * @param id
     * @return
     */
    @PostMapping("/pwd")
    @ResponseBody
    public Map<String,Object> pwd(String sourcePwd,String newPwd,String type,Integer id){
        System.out.println(sourcePwd+","+newPwd+","+type+","+id);
        if("1".equals(type)){
            User user = userService.detail(id);
            if(user.getUserPwd().equals(MD5Utils.getMD5(sourcePwd))){
                User entity = new User();
                entity.setId(id);
                entity.setUserPwd(MD5Utils.getMD5(newPwd));
                int update = userService.update(entity);
                if(update>0){
                    return MapControl.getInstance().success().getMap();
                }else {
                    return MapControl.getInstance().error().getMap();
                }
            }else {
                return MapControl.getInstance().error("原密码错误").getMap();
            }
        }


        if("2".equals(type)){
            Teacher teacher = teacherService.detail(id);
            if(teacher.getTeacherPwd().equals(MD5Utils.getMD5(sourcePwd))){
                Teacher entity = new Teacher();
                entity.setId(id);
                entity.setTeacherPwd(MD5Utils.getMD5(newPwd));
                int update = teacherService.update(entity);
                if(update>0){
                    return MapControl.getInstance().success().getMap();
                }else {
                    return MapControl.getInstance().error().getMap();
                }
            }else {
                return MapControl.getInstance().error("原密码错误").getMap();
            }
        }
        if("3".equals(type)){
            Student student = studentService.detail(id);
            if(student.getStuPwd().equals(MD5Utils.getMD5(sourcePwd))){
                Student entity = new Student();
                entity.setId(id);
                entity.setStuPwd(MD5Utils.getMD5(newPwd));
                int update = studentService.update(entity);
                if(update>0){
                    return MapControl.getInstance().success().getMap();
                }else {
                    return MapControl.getInstance().error().getMap();
                }
            }else {
                return MapControl.getInstance().error("原密码错误").getMap();
            }
        }
        return MapControl.getInstance().error().getMap();
    }
}
