package com.example.team_projectdemo02.controller;

import com.example.team_projectdemo02.entity.Student;
import com.example.team_projectdemo02.mapping.StudentMapper;
import com.example.team_projectdemo02.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

    final StudentService studentService;
    @PostMapping("add")

    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentService.add(student);

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable String id){

        if (studentService.findById(id) != null) {
            return ResponseEntity.ok(studentService.findById(id));
        }else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("findAll")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @PutMapping("update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        studentService.update(student);
        return ResponseEntity.ok(student);
    }
}