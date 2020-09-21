package com.yzh1024.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换器
 * @author yzh1024
 * @date 2020/9/20
 **/
public class DateConverter implements Converter<String, Date> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Date convert(String source) {
        try {
            if(StringUtils.hasLength(source)&&source.indexOf(":")>0){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.parse(source);
            }else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse(source);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }
}
