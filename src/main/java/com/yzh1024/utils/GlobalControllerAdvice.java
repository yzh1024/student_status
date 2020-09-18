package com.yzh1024.utils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.HandlerChain;
import java.util.Map;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    private final String ERROR = "error";

    /**
     * 权限异常,跳转页面的形式
     * @param e
     * @return
     */
    @ExceptionHandler(value=PermissionException.class)
    public ModelAndView noPermission(PermissionException e){
        ModelAndView modelAndView = new ModelAndView(ERROR);
        modelAndView.addObject(ERROR,e.getMessage());
        return modelAndView;
    }

    /**
     * 运行时异常，返回json的形式
     * @param e
     * @return
     */
    @ExceptionHandler(value=RuntimeException.class)
    @ResponseBody//返回json数据
    public Map<String,Object> runtimeException(RuntimeException e){
        return MapControl.getInstance().error(e.getMessage()).getMap();
    }


}
