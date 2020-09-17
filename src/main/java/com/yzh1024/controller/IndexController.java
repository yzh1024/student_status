package com.yzh1024.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yzh1024
 * @date 2020/9/14
 **/

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
