package com.yzh1024.controller;

import com.yzh1024.entity.Section;
import com.yzh1024.service.SectionService;
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
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @PostMapping("/create")
    @ResponseBody
    private Map<String, Object> create(Section section) {
        int result = sectionService.create(section);
        //新增失败
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().put("date",new Date()).getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    private Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = sectionService.delete(id);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    private Map<String, Object> update(Section section) {
        int result = sectionService.update(section);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/detail/{id}")
    @ResponseBody
    private Map<String, Object> detail(@PathVariable("id") Integer id) {
        Section section = sectionService.detail(id);
        if(section ==null){
            return MapControl.getInstance().nodata().getMap();
        }
        return MapControl.getInstance().success().put("data",section).getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(Section section) {
        List<Section> list = sectionService.query(section);
        return MapControl.getInstance().success().put("data",list).getMap();
    }

}














