package com.example.team_projectdemo02.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.team_projectdemo02.entity.Student;
import com.example.team_projectdemo02.mapping.StudentMapper;
import com.example.team_projectdemo02.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor

public class StudentController {

    final StudentService studentService;
    @PostMapping("redis/add")
    public ResponseEntity<Student> addStudentRedis(@RequestBody Student student) {

        studentService.addStudentRedis(student);

        return ResponseEntity.ok(student);


    }

    @DeleteMapping("redis/delete/{id}")
    public ResponseEntity<Void> deleteStudentRedis(@PathVariable String id) {

        studentService.deleteStudentRedis(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("redis/get/{id}")
    public ResponseEntity<Student> getStudentRedis(@PathVariable String id){
        return ResponseEntity.ok(studentService.getStudentRedis(id));
    }

    @GetMapping("findAll")
    public IPage<Student> studentIPage(@PathVariable String id) {
        
    }


    @PutMapping("redis/update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){

        studentService.updateStudentRedis(student);

        return ResponseEntity.ok(student);
    }
}