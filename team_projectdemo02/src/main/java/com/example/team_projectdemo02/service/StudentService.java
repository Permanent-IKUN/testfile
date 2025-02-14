package com.example.team_projectdemo02.service;

import com.example.team_projectdemo02.entity.Student;

import java.util.List;

public interface StudentService {
    void add(Student student);

    void delete(String id);

    Student findById(String id);

    List<Student> findAll();

    void  update(Student student);
}
