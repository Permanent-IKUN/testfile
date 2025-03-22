package com.example.team_projectdemo02.controller;

import com.example.team_projectdemo02.entity.Student;
import com.example.team_projectdemo02.model.BasicPageResultVO;
import com.example.team_projectdemo02.model.PageStudent;
import com.example.team_projectdemo02.model.ResponseVO;
import com.example.team_projectdemo02.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor

public class StudentController {

    final StudentService studentService;
    @PostMapping("add")
    public ResponseVO<Object> add(@RequestBody @Validated Student student){
        studentService.add(student);
        return ResponseVO.SUCCESS();
    }

    @DeleteMapping("delete/{id}")
    public ResponseVO<?> deleteByStudentId(@PathVariable @Validated String id){
        studentService.delete(id);
        return ResponseVO.SUCCESS();
//        return ResponseEntity.ok().build();
    }

    @PostMapping("redis/add")
    public ResponseEntity<Student> addStudentRedis(@RequestBody @Validated Student student) {

        studentService.addStudentRedis(student);

        return ResponseEntity.ok(student);


    }

    @DeleteMapping("redis/delete/{id}")
    public ResponseEntity<Void> deleteStudentRedis(@PathVariable @Validated String id) {

        studentService.deleteStudentRedis(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("redis/get/{id}")
    public ResponseEntity<Student> getStudentRedis(@PathVariable @Validated String id){
        return ResponseEntity.ok(studentService.getStudentRedis(id));
    }

    @GetMapping("/list")
    public ResponseVO<BasicPageResultVO<Student>> getStudentPage(PageStudent PageStudent) {
        if (studentService.getStudentPage(PageStudent)==null){
            return ResponseVO.FAIL();
        }
        return ResponseVO.SUCCESS(studentService.getStudentPage(PageStudent));
    }



    @PutMapping("redis/update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){

        studentService.updateStudentRedis(student);

        return ResponseEntity.ok(student);
    }


}