package com.yzh1024.service;

import com.yzh1024.dao.StudentDao;
import com.yzh1024.entity.Student;
import com.yzh1024.utils.BeanMapUtils;
import com.yzh1024.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yzh1024
 * @date 2020/9/10
 **/
@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    public int create(Student pi){
        return studentDao.create(pi);
    }

    public int delete(Integer id){
        return studentDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Student student){
        return studentDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(student)).addId(student.getId()).getMap());
    }

    public List<Student> query(Student student){
        return studentDao.query(BeanMapUtils.beanToMap(student));
    }

    public Student detail(Integer id){
        return studentDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public Student login(String userName, String password){
        Map<String, Object> map = MapParameter.getInstance()
                .add("stuNo", userName)
                .add("stuPwd", password)
                .getMap();
        return studentDao.detail(map);
    }

    public int count(Student student){
        return studentDao.count(BeanMapUtils.beanToMap(student));
    }
    
}
