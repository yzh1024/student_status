package com.yzh1024.service;

import com.github.pagehelper.PageHelper;
import com.yzh1024.dao.StudentDao;
import com.yzh1024.entity.Student;
import com.yzh1024.utils.BeanMapUtils;
import com.yzh1024.utils.MD5Utils;
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

    /**
     * 创建用户，密码加密处理
     * @param pi
     * @return
     */
    public int create(Student pi){
        pi.setStuPwd(MD5Utils.getMD5(pi.getStuPwd()));
        return studentDao.create(pi);
    }

    public int delete(Integer id){
        return studentDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Student student){
        return studentDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(student)).addId(student.getId()).getMap());
    }

    public List<Student> query(Student student){
        if (student != null && student.getPage() != null){
            PageHelper.startPage(student.getPage(), student.getLimit());
        }
        return studentDao.query(BeanMapUtils.beanToMap(student));
    }

    public Student detail(Integer id){
        return studentDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Student student){
        return studentDao.count(BeanMapUtils.beanToMap(student));
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
            flag = studentDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }
    
}
