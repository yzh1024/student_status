package com.yzh1024.service;

import com.github.pagehelper.PageHelper;
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

    public int create(Subject pi) {
        return subjectDao.create(pi);
    }

    public int delete(Integer id) {
        return subjectDao.delete(MapParameter.getInstance().addId(id).getMap());
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
            flag = subjectDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }

    public int update(Subject subject) {
        return subjectDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(subject)).addId(subject.getId()).getMap());
    }

    public List<Subject> query(Subject subject) {
        if (subject != null && subject.getPage() != null) {
            PageHelper.startPage(subject.getPage(), subject.getLimit());
        }
        return subjectDao.query(BeanMapUtils.beanToMap(subject));
    }

    public Subject detail(Integer id) {
        return subjectDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Subject subject) {
        return subjectDao.count(BeanMapUtils.beanToMap(subject));
    }

}
