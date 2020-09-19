package com.yzh1024.controller;

import com.yzh1024.entity.Clazz;
import com.yzh1024.entity.Clazz;
import com.yzh1024.entity.Subject;
import com.yzh1024.service.ClazzService;
import com.yzh1024.service.SubjectService;
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
@RequestMapping("/clazz")
public class ClazzController {

    private final String LIST = "clazz/list";
    private final String ADD = "clazz/add";
    private final String UPDATE = "clazz/update";

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private SubjectService subjectService;

    /**
     * 跳转到clazz/list.jsp
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return "clazz/list";
    }

    /**
     * 新增，先查出专业，然后跳到clazz/add.jsp页面
     * @return
     */
    @GetMapping("/add")
    private String add(ModelMap modelMap ){
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects",subjects);
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
        int result = clazzService.delete(ids);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    /**
     * 修改信息
     * 先根据id查询信息，然后跳转到clazz/update.jsp页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    private String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Clazz clazz = clazzService.detail(id);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("clazz",clazz);
        modelMap.addAttribute("subjects",subjects);
        return UPDATE;
    }


    @PostMapping("/create")
    @ResponseBody
    private Map<String, Object> create(Clazz clazz) {
        int result = clazzService.create(clazz);
        //新增失败
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().put("date",new Date()).getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    private Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = clazzService.delete(id);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }



    @PostMapping("/update")
    @ResponseBody
    private Map<String, Object> update(Clazz clazz) {
        int result = clazzService.update(clazz);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(Clazz clazz) {
        List<Clazz> list = clazzService.query(clazz);
        List<Subject> subjects = subjectService.query(null);
        //设置subject,循环赋值
        list.forEach(entity->{
            subjects.forEach(subject -> {
                if(entity.getSubjectId() == subject.getId().intValue()){
                    entity.setSubject(subject);
                }
            });
        });
        Integer count = clazzService.count(clazz);
        return MapControl.getInstance().success().put("data",list).put("count",count).getMap();
    }
   
 

}














