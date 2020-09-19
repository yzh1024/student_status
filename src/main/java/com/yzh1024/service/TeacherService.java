package com.yzh1024.service;

import com.github.pagehelper.PageHelper;
import com.yzh1024.dao.TeacherDao;
import com.yzh1024.entity.Teacher;
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
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    public int create(Teacher pi){
        return teacherDao.create(pi);
    }

    public int delete(Integer id){
        return teacherDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Teacher teacher){
        return teacherDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(teacher)).addId(teacher.getId()).getMap());
    }

    public List<Teacher> query(Teacher teacher){
        if (teacher != null && teacher.getPage() != null){
            PageHelper.startPage(teacher.getPage(), teacher.getLimit());
        }
        return teacherDao.query(BeanMapUtils.beanToMap(teacher));
    }

    public Teacher detail(Integer id){
        return teacherDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Teacher teacher){
        return teacherDao.count(BeanMapUtils.beanToMap(teacher));
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public Teacher login(String userName, String password){
        Map<String, Object> map = MapParameter.getInstance()
                .add("teacherName", userName)
                .add("teacherPwd", password)
                .getMap();
        return teacherDao.detail(map);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public int delete(String ids) {
        //按照“,”将传过来的字符串分割
        String[] arr = ids.split(",");
        int flag = 0;
        for (String s : arr) {
            flag = teacherDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }


}
