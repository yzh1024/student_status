package com.yzh1024.service;

import com.yzh1024.dao.SubjectDao;
import com.yzh1024.entity.Subject;
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
public class SubjectService {
    @Autowired
    private SubjectDao subjectDao;
    public int create(Subject pi){
        return subjectDao.create(pi);
    }

    public int delete(Integer id){
        return subjectDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Subject subject){
        return subjectDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(subject)).addId(subject.getId()).getMap());
    }

    public List<Subject> query(Subject subject){
        return subjectDao.query(BeanMapUtils.beanToMap(subject));
    }

    public Subject detail(Integer id){
        return subjectDao.detail(MapParameter.getInstance().addId(id).getMap());
    }
    public int count(Subject subject){
        return subjectDao.count(BeanMapUtils.beanToMap(subject));
    }
    
}
