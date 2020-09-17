package com.yzh1024.controller;

import com.yzh1024.entity.Job;
import com.yzh1024.service.JobService;
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
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/create")
    @ResponseBody
    private Map<String, Object> create(Job job) {
        int result = jobService.create(job);
        //新增失败
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().put("date",new Date()).getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    private Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = jobService.delete(id);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    private Map<String, Object> update(Job job) {
        int result = jobService.update(job);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/detail/{id}")
    @ResponseBody
    private Map<String, Object> detail(@PathVariable("id") Integer id) {
        Job job = jobService.detail(id);
        if(job ==null){
            return MapControl.getInstance().nodata().getMap();
        }
        return MapControl.getInstance().success().put("data",job).getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(Job job) {
        List<Job> list = jobService.query(job);
        return MapControl.getInstance().success().put("data",list).getMap();
    }

}














