package com.yzh1024.controller;

import com.yzh1024.entity.Subject;
import com.yzh1024.service.SubjectService;
import com.yzh1024.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private SubjectService subjectService;

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

    @PostMapping("/detail/{id}")
    @ResponseBody
    private Map<String, Object> detail(@PathVariable("id") Integer id) {
        Subject subject = subjectService.detail(id);
        if(subject ==null){
            return MapControl.getInstance().nodata().getMap();
        }
        return MapControl.getInstance().success().put("data",subject).getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(Subject subject) {
        List<Subject> list = subjectService.query(subject);
        return MapControl.getInstance().success().put("data",list).getMap();
    }

}














