package com.yzh1024.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/12
 **/
public class MapControl {

    /**
     * 自定义状态码
     */
    private static final String SUCCESS = "1000";
    private static final String ERROR = "1001";
    private static final String NODATA = "1002";

    /**
     * 目标对象
     */
    private Map<String, Object> paramMap = new HashMap<>();

    /**
     * 私有构造
     */
    private MapControl() {

    }

    /**
     * 对外的函数，返回MapParameter对象,getInstance每被调用一次，就new一个MapParameter，就产生一个Map对象
     *
     * @return
     */
    public static MapControl getInstance() {
        return new MapControl();
    }


    public MapControl addId(Object value){
        paramMap.put("id",value);
        return this;
    }

    public MapControl success(){
        paramMap.put("code",SUCCESS);
        paramMap.put("msg","操作成功");
        return this;
    }

    public MapControl error(){
        paramMap.put("code",ERROR);
        paramMap.put("msg","操作失败");
        return this;
    }

    public MapControl error(String msg){
        paramMap.put("code",ERROR);
        paramMap.put("msg",msg);
        return this;
    }

    public MapControl nodata(){
        paramMap.put("code",NODATA);
        paramMap.put("msg","暂无数据");
        return this;
    }

    public MapControl add(String key, Object value){
        paramMap.put(key,value);
        return this;
    }

    public MapControl put(String key, Object value){
        return this.add(key, value);
    }

    public MapControl add(Map<String,Object> map){
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            paramMap.put(entry.getKey(),entry.getValue());
        }
        return this;
    }

    public MapControl put(Map<String,Object> map){
        return this.add(map);
    }

    /**
     * 最终返回这个Map对象
     * @return
     */
    public Map<String,Object> getMap(){
        return paramMap;
    }
}
