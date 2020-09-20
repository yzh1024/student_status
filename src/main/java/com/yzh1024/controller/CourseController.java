package com.yzh1024.controller;

import com.yzh1024.entity.Course;
import com.yzh1024.entity.Course;
import com.yzh1024.service.CourseService;
import com.yzh1024.service.CourseService;
import com.yzh1024.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/10
 **/

@Controller
@RequestMapping("/course")
public class CourseController {
    
    private final String LIST = "course/list";
    private final String ADD = "course/add";
    private final String UPDATE = "course/update";

    @Autowired
    private CourseService courseService;


    /**
     * 跳转到course/list.jsp
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return LIST;
    }
    /**
     * 新增，跳到course/add.jsp页面
     * @return
     */
    @GetMapping("/add")
    private String add(){
        return ADD;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    private Map<String, Object> delete(String ids) {
        int result = courseService.delete(ids);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    /**
     * 修改信息
     * 先根据id查询信息，然后跳转到course/update.jsp页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    private String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Course course = courseService.detail(id);
        modelMap.addAttribute("course",course);
        return UPDATE;
    }

    @PostMapping("/create")
    @ResponseBody
    private Map<String, Object> create(Course course) {
        int result = courseService.create(course);
        //新增失败
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().put("date",new Date()).getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    private Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = courseService.delete(id);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    private Map<String, Object> update(Course course) {
        int result = courseService.update(course);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/detail/{id}")
    @ResponseBody
    private Map<String, Object> detail(@PathVariable("id") Integer id) {
        Course course = courseService.detail(id);
        if(course ==null){
            return MapControl.getInstance().nodata().getMap();
        }
        return MapControl.getInstance().success().put("data",course).getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(Course course) {
        List<Course> list = courseService.query(course);
        return MapControl.getInstance().success().put("data",list).getMap();
    }

}














