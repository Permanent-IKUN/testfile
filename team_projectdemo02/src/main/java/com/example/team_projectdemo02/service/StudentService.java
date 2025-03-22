package com.example.team_projectdemo02.service;

import com.example.team_projectdemo02.entity.Student;
import com.example.team_projectdemo02.model.BasicPageResultVO;
import com.example.team_projectdemo02.model.PageStudent;

public interface StudentService {

    void add(Student student);

    void delete(String id);
    void addStudentRedis(Student student);

    void deleteStudentRedis(String id);

    Student getStudentRedis(String id);


    void  updateStudentRedis(Student student);

    BasicPageResultVO<Student> getStudentPage(PageStudent pageStudent);
}
