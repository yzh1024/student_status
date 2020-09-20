package com.yzh1024.controller;

import com.yzh1024.entity.Teacher;
import com.yzh1024.entity.Teacher;
import com.yzh1024.service.TeacherService;
import com.yzh1024.service.TeacherService;
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
@RequestMapping("/teacher")
public class TeacherController {

    private final String LIST = "teacher/list";
    private final String ADD = "teacher/add";
    private final String UPDATE = "teacher/update";

    @Autowired
    private TeacherService teacherService;


    /**
     * 跳转到teacher/list.jsp
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return LIST;
    }
    /**
     * 新增，跳到teacher/add.jsp页面
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
        int result = teacherService.delete(ids);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    /**
     * 修改信息
     * 先根据id查询信息，然后跳转到teacher/update.jsp页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    private String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Teacher teacher = teacherService.detail(id);
        modelMap.addAttribute("teacher",teacher);
        return UPDATE;
    }


    @PostMapping("/create")
    @ResponseBody
    private Map<String, Object> create(Teacher teacher) {
        int result = teacherService.create(teacher);
        //新增失败
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().put("date",new Date()).getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    private Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = teacherService.delete(id);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }



    @PostMapping("/update")
    @ResponseBody
    private Map<String, Object> update(Teacher teacher) {
        int result = teacherService.update(teacher);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(Teacher teacher) {
        List<Teacher> list = teacherService.query(teacher);
        Integer count = teacherService.count(teacher);
        return MapControl.getInstance().success().put("data",list).put("count",count).getMap();
    }

}














