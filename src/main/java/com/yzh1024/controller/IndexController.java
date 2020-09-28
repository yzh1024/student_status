package com.yzh1024.controller;

import com.yzh1024.entity.*;
import com.yzh1024.service.*;
import com.yzh1024.utils.MD5Utils;
import com.yzh1024.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/14
 **/

@Controller
public class IndexController {

    @Autowired
    UserService userService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    @Autowired
    ClazzService clazzService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    CourseService courseService;
    @Autowired
    SectionService sectionService;
    @Autowired
    ScoreService scoreService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/pwd")
    public String pwd() {
        return "pwd";
    }

    @GetMapping("/main")
    public String main(ModelMap modelMap) {
        //系统数据概览
        List<Clazz> clazzes = clazzService.query(null);
        List<Student> students = studentService.query(null);
        List<Teacher> teachers = teacherService.query(null);
        List<Subject> subjects = subjectService.query(null);
        List<Section> sections = sectionService.query(null);
        List<Course> courses = courseService.query(null);
        modelMap.put("clazzCnt", clazzes.size());
        modelMap.put("studentCnt", students.size());
        modelMap.put("teacherCnt", teachers.size());
        modelMap.put("subjectCnt", subjects.size());
        modelMap.put("sectionCnt", sections.size());
        modelMap.put("courseCnt", courses.size());

        //每个班级的学生数量
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Clazz clazz : clazzes) {
            Map<String, Object> map = new HashMap<>();
            map.put("name",clazz.getClazzName());
            int cnt = 0;
            for (Student student : students) {
                if(student.getClazzId() == clazz.getId().intValue()){
                    cnt++;
                }
            }
            map.put("cnt",cnt);
            mapList.add(map);
        }
        modelMap.put("mapList",mapList);

        //查询各科平均成绩
        List<HashMap> mapList2 = scoreService.queryAvgBySection();
        modelMap.put("mapList2",mapList2);

        return "main";
    }


    /**
     * 修改密码
     *
     * @param sourcePwd
     * @param newPwd
     * @param type
     * @param id
     * @return
     */
    @PostMapping("/pwd")
    @ResponseBody
    public Map<String, Object> pwd(String sourcePwd, String newPwd, String type, Integer id) {
        System.out.println(sourcePwd + "," + newPwd + "," + type + "," + id);
        if ("1".equals(type)) {
            User user = userService.detail(id);
            if (user.getUserPwd().equals(MD5Utils.getMD5(sourcePwd))) {
                User entity = new User();
                entity.setId(id);
                entity.setUserPwd(MD5Utils.getMD5(newPwd));
                int update = userService.update(entity);
                if (update > 0) {
                    return MapControl.getInstance().success().getMap();
                } else {
                    return MapControl.getInstance().error().getMap();
                }
            } else {
                return MapControl.getInstance().error("原密码错误").getMap();
            }
        }


        if ("2".equals(type)) {
            Teacher teacher = teacherService.detail(id);
            if (teacher.getTeacherPwd().equals(MD5Utils.getMD5(sourcePwd))) {
                Teacher entity = new Teacher();
                entity.setId(id);
                entity.setTeacherPwd(MD5Utils.getMD5(newPwd));
                int update = teacherService.update(entity);
                if (update > 0) {
                    return MapControl.getInstance().success().getMap();
                } else {
                    return MapControl.getInstance().error().getMap();
                }
            } else {
                return MapControl.getInstance().error("原密码错误").getMap();
            }
        }
        if ("3".equals(type)) {
            Student student = studentService.detail(id);
            if (student.getStuPwd().equals(MD5Utils.getMD5(sourcePwd))) {
                Student entity = new Student();
                entity.setId(id);
                entity.setStuPwd(MD5Utils.getMD5(newPwd));
                int update = studentService.update(entity);
                if (update > 0) {
                    return MapControl.getInstance().success().getMap();
                } else {
                    return MapControl.getInstance().error().getMap();
                }
            } else {
                return MapControl.getInstance().error("原密码错误").getMap();
            }
        }
        return MapControl.getInstance().error().getMap();
    }
}
