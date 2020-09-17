package com.yzh1024.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean转为Map
 * @author yzh1024
 * @date 2020/9/12
 **/
public class BeanMapUtils {
    /**
     * 将对象装换为map
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap();
        if (bean != null) {
//            BeanMap是spring提供的Bean转换为Map的类
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    public static <T> Map<String, Object> beanToMapForUpdate(T bean) {
        Map<String, Object> map = new HashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put("update"+upperFirstLatter(key+""),beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 首字母转大写
     * @param letter
     * @return
     */
    public static String upperFirstLatter(String letter){
        char[] chars = letter.toCharArray();
        if(chars[0]>='a' && chars[0]<='z'){
            chars[0] = (char) (chars[0]-32);
        }
        return new String(chars);
    }

}
