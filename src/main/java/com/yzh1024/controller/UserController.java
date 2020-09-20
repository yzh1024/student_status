package com.yzh1024.controller;

import com.yzh1024.entity.User;
import com.yzh1024.entity.User;
import com.yzh1024.service.UserService;
import com.yzh1024.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    private final String LIST = "user/list";
    private final String ADD = "user/add";
    private final String UPDATE = "user/update";

    @Autowired
    private UserService userService;


    /**
     * 跳转到user/list.jsp
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return LIST;
    }
    /**
     * 新增，跳到user/add.jsp页面
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
        int result = userService.delete(ids);
        if(result<=0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    /**
     * 修改信息
     * 先根据id查询信息，然后跳转到user/update.jsp页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    private String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        User user = userService.detail(id);
        modelMap.addAttribute("user",user);
        return UPDATE;
    }


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

    @PostMapping("/query")
    @ResponseBody
    private Map<String, Object> query(User user) {
        List<User> list = userService.query(user);
        Integer count = userService.count(user);
        return MapControl.getInstance().success().put("data",list).put("count",count).getMap();
    }
}














