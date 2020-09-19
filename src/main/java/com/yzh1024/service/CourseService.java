package com.yzh1024.service;


import com.github.pagehelper.PageHelper;
import com.yzh1024.dao.CourseDao;
import com.yzh1024.entity.Course;
import com.yzh1024.utils.BeanMapUtils;
import com.yzh1024.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yzh1024
 * @date 2020/9/10
 **/
@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;

    public int create(Course pi){
        return courseDao.create(pi);
    }

    public int delete(Integer id){
        return courseDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Course course){
        return courseDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(course)).addId(course.getId()).getMap());
    }

    public List<Course> query(Course course){
        if (course != null && course.getPage() != null){
            PageHelper.startPage(course.getPage(), course.getLimit());
        }
        return courseDao.query(BeanMapUtils.beanToMap(course));
    }

    public Course detail(Integer id){
        return courseDao.detail(MapParameter.getInstance().addId(id).getMap());
    }
    public int count(Course course){
        return courseDao.count(BeanMapUtils.beanToMap(course));
    }
    
}
