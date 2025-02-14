package com.example.team_projectdemo02.service.impl;

import com.example.team_projectdemo02.entity.Student;
import com.example.team_projectdemo02.mapping.StudentMapper;
import com.example.team_projectdemo02.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Studentlmpl implements StudentService {

    final StudentMapper studentMapper;
    @Override
    public void add(Student student) {
        studentMapper.insertStudent(student);
    }

    @Override
    public void delete(String id) {
        studentMapper.deleteStudentById(id);
    }

    @Override
    public Student findById(String id){
        studentMapper.findById(id);
        if (id != null){
            return studentMapper.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public List<Student> findAll(){
        return studentMapper.findAllStudents();
    }

    @Override
    public void update(Student student){
        studentMapper.update(student);
    }
}
