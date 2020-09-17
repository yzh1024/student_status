package com.yzh1024.controller;

import com.yzh1024.entity.User;
import com.yzh1024.service.UserService;
import com.yzh1024.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/10
 **/

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @ResponseBody
    private Map<String, Object> create(User user) {
        int result = userService.create(user);
        //新增失败
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().put("date",new Date()).getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    private Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = userService.delete(id);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    private Map<String, Object> update(User user) {
        int result = userService.update(user);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/detail/{id}")
    @ResponseBody
    private Map<String, Object> detail(@PathVariable("id") Integer id) {
        User user = userService.detail(id);
        if(user ==null){
            return MapControl.getInstance().nodata().getMap();
        }
        return MapControl.getInstance().success().put("data",user).getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(User user) {
        List<User> list = userService.query(user);
        return MapControl.getInstance().success().put("data",list).getMap();
    }

}














