package com.yzh1024.utils;

import sun.security.jca.GetInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/12
 **/
public class MapParameter {
    /**
     * 目标对象
     */
    private Map<String, Object> paramMap = new HashMap<>();

    /**
     * 私有构造
     */
    private MapParameter() {

    }

    /**
     * 对外的函数，返回MapParameter对象,getInstance每被调用一次，就new一个MapParameter，就产生一个Map对象
     *
     * @return
     */
    public static MapParameter getInstance() {
        return new MapParameter();
    }


    public MapParameter addId(Object value){
        paramMap.put("id",value);
        return this;
    }

    public MapParameter add(String key,Object value){
        paramMap.put(key,value);
        return this;
    }
    public MapParameter add(Map<String,Object> map){
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            paramMap.put(entry.getKey(),entry.getValue());
        }
        return this;
    }

    /**
     * 最终返回这个Map对象
     * @return
     */
    public Map<String,Object> getMap(){
        return paramMap;
    }
}
