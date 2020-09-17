package com.yzh1024.dao;

import java.util.List;
import java.util.Map;

import com.yzh1024.entity.Subject;

public interface SubjectDao {
    public int create(Subject pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Subject> query(Map<String, Object> paramMap);

    public Subject detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);
}