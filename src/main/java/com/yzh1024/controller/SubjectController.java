package com.yzh1024.controller;

import com.yzh1024.entity.Subject;
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
@RequestMapping("/subject")
public class SubjectController {

    private final String LIST = "subject/list";
    private final String ADD = "subject/add";
    private final String UPDATE = "subject/update";

    @Autowired
    private SubjectService subjectService;


    /**
     * 跳转到subject/list.jsp
      * @return
     */
    @GetMapping("/list")
    public String list(){
        return LIST;
    }
    /**
     * 新增，跳到subject/add.jsp页面
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
        int result = subjectService.delete(ids);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    /**
     * 修改信息
     * 先根据id查询信息，然后跳转到subject/update.jsp页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    private String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Subject subject = subjectService.detail(id);
        modelMap.addAttribute("subject",subject);
        return UPDATE;
    }


    @PostMapping("/create")
    @ResponseBody
    private Map<String, Object> create(Subject subject) {
        int result = subjectService.create(subject);
        //新增失败
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().put("date",new Date()).getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    private Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = subjectService.delete(id);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }



    @PostMapping("/update")
    @ResponseBody
    private Map<String, Object> update(Subject subject) {
        int result = subjectService.update(subject);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(Subject subject) {
        List<Subject> list = subjectService.query(subject);
        Integer count = subjectService.count(subject);
        return MapControl.getInstance().success().put("data",list).put("count",count).getMap();
    }

}














