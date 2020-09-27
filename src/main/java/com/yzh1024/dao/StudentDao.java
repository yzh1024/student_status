package com.yzh1024.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yzh1024.entity.Student;

public interface StudentDao {
    public int create(Student pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Student> query(Map<String, Object> paramMap);

    public Student detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    /**
     * 查询选课的学生信息
     * @param paramMap
     * @return
     */
    public List<HashMap> querySelectStudent(Map<String, Object> paramMap);


    /**
     * 老师查询学生信息
     * @param paramMap
     * @return
     */
    public List<Student> queryStudentByTeacher(Map<String, Object> paramMap);
}