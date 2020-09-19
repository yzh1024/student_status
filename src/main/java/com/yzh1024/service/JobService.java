package com.yzh1024.service;

import com.github.pagehelper.PageHelper;
import com.yzh1024.dao.JobDao;
import com.yzh1024.entity.Job;
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
public class JobService {
    @Autowired
    private JobDao jobDao;

    public int create(Job pi) {
        return jobDao.create(pi);
    }

    public int delete(Integer id) {
        return jobDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Job job) {
        return jobDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(job)).addId(job.getId()).getMap());
    }

    public List<Job> query(Job job) {
        if (job != null && job.getPage() != null) {
            PageHelper.startPage(job.getPage(), job.getLimit());
        }
        return jobDao.query(BeanMapUtils.beanToMap(job));
    }

    public Job detail(Integer id) {
        return jobDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Job job) {
        return jobDao.count(BeanMapUtils.beanToMap(job));
    }

}
