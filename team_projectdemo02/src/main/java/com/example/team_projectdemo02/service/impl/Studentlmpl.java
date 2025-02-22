package com.example.team_projectdemo02.service.impl;

import com.example.team_projectdemo02.entity.Student;
import com.example.team_projectdemo02.mapping.StudentMapper;
import com.example.team_projectdemo02.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Studentlmpl implements StudentService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    final StudentMapper studentMapper;
    @Override
    public void addStudentRedis(Student student) {
        try {
            String key = "student:" + student.getId();
            String json = objectMapper.writeValueAsString(student);
            stringRedisTemplate.opsForValue().set(key, json);
        } catch (IOException e) {
            throw new RuntimeException("序列化失败", e);
        }
    }
    @Override
    public void deleteStudentRedis(String id) {
        stringRedisTemplate.delete("student:" + id);
    }

    @Override
    public Student getStudentRedis(String id){
        String json = stringRedisTemplate.opsForValue().get(id);
        if (json != null) {
            try {
                return objectMapper.readValue(json, Student.class);
            }catch (IOException e){
                throw new RuntimeException("反序列化失败:" + json, e );
            }

        }else {
            return null;
        }
    }


    @Override
    public List<Student> querypage(String id){

    }

    @Override
    public void updateStudentRedis(Student student){

        stringRedisTemplate.opsForValue().set("student:" + student.getId(),student.toString());

    }

}
