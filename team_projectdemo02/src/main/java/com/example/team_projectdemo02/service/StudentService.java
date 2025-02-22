package com.example.team_projectdemo02.service;

import com.example.team_projectdemo02.entity.Student;

import java.util.List;

public interface StudentService {
    void addStudentRedis(Student student);

    void deleteStudentRedis(String id);

    Student getStudentRedis(String id);

    List<Student> querypage(String id);

    void  updateStudentRedis(Student student);
}
