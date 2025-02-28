package com.example.team_projectdemo02.controller;

import com.example.team_projectdemo02.entity.Student;
import com.example.team_projectdemo02.model.BasicPageResultVO;
import com.example.team_projectdemo02.model.PageStudent;
import com.example.team_projectdemo02.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor

public class StudentController {

    final StudentService studentService;
    @PostMapping("add")
    public ResponseEntity<Student> add(@RequestBody Student student){
        studentService.add(student);
        return ResponseEntity.ok(student);
    }

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

    @GetMapping("/list")
    public ResponseEntity<BasicPageResultVO> getStudentPage(PageStudent PageStudent) {
        return ResponseEntity.ok(studentService.getStudentPage(PageStudent));
    }



    @PutMapping("redis/update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){

        studentService.updateStudentRedis(student);

        return ResponseEntity.ok(student);
    }


}