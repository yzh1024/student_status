package com.yzh1024.controller;

import com.yzh1024.entity.*;
import com.yzh1024.entity.Student;
import com.yzh1024.entity.Student;
import com.yzh1024.service.*;
import com.yzh1024.service.StudentService;
import com.yzh1024.service.StudentService;
import com.yzh1024.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/10
 **/

@Controller
@RequestMapping("/student")
public class StudentController {

    private final String LIST = "student/list";
    private final String ADD = "student/add";
    private final String UPDATE = "student/update";

    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;

    /**
     * 跳转到student/list.jsp
     *
     * @return
     */
    @GetMapping("/list")
    public String list() {
        return "student/list";
    }

    /**
     * 跳转到student/teacher_student.jsp
     *
     * @return
     */
    @GetMapping("/teacher_student")
    public String teacher_student(HttpSession session, ModelMap modelMap){
        Teacher teacher = (Teacher) session.getAttribute("user");
        List<Clazz> clazzes = clazzService.query(null);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("clazzes",clazzes);
        modelMap.addAttribute("subjects",subjects);
        modelMap.addAttribute("teacherId",teacher.getId());
        return "student/teacher_student";
    }

    @PostMapping("/teacher_student")
    @ResponseBody
    public Map<String,Object> teacher_student(Integer clazzId,Integer subjectId,HttpSession session){

        Teacher teacher = (Teacher) session.getAttribute("user");
        List<Student> students = studentService.queryStudentByTeacher(teacher.getId(), clazzId, subjectId);
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        //设置关联关系
        students.forEach(entity->{
            subjects.forEach(subject -> {
                if(subject.getId() == entity.getSubjectId()){
                    entity.setSubject(subject);
                }
            });
            clazzes.forEach(clazz -> {
                if(clazz.getId() == entity.getClazzId()){
                    entity.setClazz(clazz);
                }
            });
        });
        return MapControl.getInstance().success().add("data",students).getMap();
    }

    /**
     * 新增，先查出专业，然后跳到student/add.jsp页面
     *
     * @return
     */
    @GetMapping("/add")
    private String add(ModelMap modelMap) {
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects", subjects);
        return ADD;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    private Map<String, Object> delete(String ids) {
        int result = studentService.delete(ids);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    /**
     * 修改信息
     * 先根据id查询信息，然后跳转到student/update.jsp页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    private String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Student student = studentService.detail(id);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("student", student);
        modelMap.addAttribute("subjects", subjects);
        return UPDATE;
    }


    @PostMapping("/create")
    @ResponseBody
    private Map<String, Object> create(Student student) {
        int result = studentService.create(student);
        //新增失败
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().put("date", new Date()).getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    private Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = studentService.delete(id);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


    @PostMapping("/update")
    @ResponseBody
    private Map<String, Object> update(Student student) {
        int result = studentService.update(student);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(Student student) {
        List<Student> studentes = studentService.query(student);
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        //设置subject,循环赋值
        studentes.forEach(entity -> {
            subjects.forEach(subject -> {
                if (entity.getSubjectId() == subject.getId().intValue()) {
                    entity.setSubject(subject);
                }
            });
            clazzes.forEach(clazz -> {
                if (entity.getClazzId() == clazz.getId().intValue()) {
                    entity.setClazz(clazz);
                }
            });
        });
        Integer count = studentService.count(student);
        return MapControl.getInstance().success().put("data", studentes).put("count", count).getMap();
    }

}














