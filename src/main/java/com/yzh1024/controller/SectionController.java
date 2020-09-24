package com.yzh1024.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzh1024.entity.*;
import com.yzh1024.entity.Section;
import com.yzh1024.service.*;
import com.yzh1024.service.SectionService;
import com.yzh1024.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author yzh1024
 * @date 2020/9/10
 **/

@Controller
@RequestMapping("/section")
public class SectionController {

    private final String LIST = "section/list";
    private final String ADD = "section/add";
    private final String UPDATE = "section/update";

    @Autowired
    private SectionService sectionService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;

    /**
     * 跳转到section/list.jsp
     *
     * @return
     */
    @GetMapping("/list")
    public String list() {
        return LIST;
    }

    /**
     * 新增，先查出老师和课程，然后跳到section/add.jsp页面
     *
     * @return
     */
    @GetMapping("/add")
    private String add(Integer clazzId, ModelMap modelMap) {
        List<Course> courses = courseService.query(null);
        List<Teacher> teachers = teacherService.query(null);
        modelMap.addAttribute("courses", courses);
        modelMap.addAttribute("teachers", teachers);
        modelMap.addAttribute("clazzId", clazzId);
        return ADD;
    }

    /**
     * 学生选课：跳转到section/student_section.jsp
     *
     * @return
     */
    @GetMapping("/student_section")
    public String student_section() {
        return "section/student_section";
    }

    @PostMapping("/query_student_section")
    @ResponseBody
    public Map<String, Object> query_student_section(HttpSession session) {
        //从session里面拿登陆对象信息
        Student student = (Student) session.getAttribute("user");
        //根据学生id查询section数据
        List<Section> sections = sectionService.queryByStudent(student.getId());
        //查询班级、老师、课程信息
        List<Clazz> clazzes = clazzService.query(null);
        List<Teacher> teachers = teacherService.query(null);
        List<Course> courses = courseService.query(null);
        //循环赋值，把班级、老师、课程对应的信息赋值到section对应的属性里面
        sections.forEach(section -> {
            clazzes.forEach(clazz -> {
                if(section.getClazzId() == clazz.getId().intValue()){
                    section.setClazz(clazz);
                }
            });
            teachers.forEach(teacher -> {
                if(section.getTeacherId() == teacher.getId().intValue()){
                    section.setTeacher(teacher);
                }
            });
            courses.forEach(course -> {
                if(section.getCourseId() == course.getId().intValue()){
                    section.setCourse(course);
                }
            });
        });

        return MapControl.getInstance().success().put("data",sections).getMap();
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
        int result = sectionService.delete(ids);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    /**
     * 修改信息
     * 先根据id查询信息，然后跳转到section/update.jsp页面
     *
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    private String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Section section = sectionService.detail(id);
        List<Course> courses = courseService.query(null);
        List<Teacher> teachers = teacherService.query(null);
        modelMap.addAttribute("courses", courses);
        modelMap.addAttribute("teachers", teachers);
        modelMap.addAttribute("section", section);
        return UPDATE;
    }


    @PostMapping("/create")
    @ResponseBody
    private Map<String, Object> create(Section section) {
        int result = sectionService.create(section);
        //新增失败
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().put("date", new Date()).getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    private Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = sectionService.delete(id);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }


    @PostMapping("/update")
    @ResponseBody
    private Map<String, Object> update(Section section) {
        int result = sectionService.update(section);
        if (result <= 0) {
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(Section section) {
        List<Section> sectiones = sectionService.query(section);
        List<Teacher> teachers = teacherService.query(null);
        List<Course> courses = courseService.query(null);

        sectiones.forEach(entity -> {
            teachers.forEach(teacher -> {
                if (entity.getTeacherId() == teacher.getId().intValue()) {
                    entity.setTeacher(teacher);
                }
            });
            courses.forEach(course -> {
                if (entity.getCourseId() == course.getId().intValue()) {
                    entity.setCourse(course);
                }
            });
        });
        Integer count = sectionService.count(section);
        return MapControl.getInstance().success().put("data", sectiones).put("count", count).getMap();
    }

    @PostMapping("/tree")
    @ResponseBody
    private List<Map<String, Object>> tree(Section section) {
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        //返回json数组
        List<Map<String, Object>> list = new ArrayList<>();

        subjects.forEach(subject -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", subject.getId());
            map.put("name", subject.getSubjectName());
            map.put("parentId", 0);
            List<Map<String, Object>> childrenList = new ArrayList<>();
            clazzes.forEach(clazz -> {
                if (clazz.getSubjectId() == subject.getId().intValue()) {
                    Map<String, Object> children = new HashMap<>();
                    children.put("id", clazz.getId());
                    children.put("name", clazz.getClazzName());
                    children.put("parentId", subject.getId());
                    childrenList.add(children);
                }
            });
            map.put("children", childrenList);
            list.add(map);
        });
        Integer count = sectionService.count(section);
        return list;
    }


}














