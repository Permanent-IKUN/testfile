package com.example.team_projectdemo02.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.team_projectdemo02.entity.Student;
import com.example.team_projectdemo02.mapping.StudentMapper;
import com.example.team_projectdemo02.model.BasicPageResultVO;
import com.example.team_projectdemo02.model.PageStudent;
import com.example.team_projectdemo02.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class Studentlmpl implements StudentService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    final StudentMapper studentMapper;


    @Override
    public void add(Student student){
        studentMapper.insertStudent(student);
    }
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
    public void updateStudentRedis(Student student){

        stringRedisTemplate.opsForValue().set("student:" + student.getId(),student.toString());

    }




//    分页查询
    @Override
    public BasicPageResultVO<Student> getStudentPage(PageStudent pageStudent){
//        获取分页参数
        Integer currentPage = pageStudent.getCurrent();
        Integer pageSize = pageStudent.getPageSize();

//        创建分页对象
        Page<Student> page = new Page<>(currentPage, pageSize);

//        创建查询条件
        QueryWrapper<Student> selectQuery = new QueryWrapper<>();

//        id不是null时，需要添加id查询条件
        if (pageStudent.getId() != null){
            selectQuery.eq("id",pageStudent.getId());
        }
        IPage<Student> findList = studentMapper.selectPage(page, selectQuery);

        return createResultV0(findList, currentPage);
    }

    public BasicPageResultVO<Student> createResultV0(IPage<Student> findList, Integer currentPage) {
        BasicPageResultVO<Student> resultVO = new BasicPageResultVO<>();

        resultVO.setCurrent(findList.getCurrent());
        resultVO.setPageSize(findList.getSize());
        resultVO.setTotal(findList.getTotal());

        long totalPage = findList.getTotal() % findList.getSize() == 0 ?
                findList.getTotal() / findList.getSize() :
                findList.getTotal() / findList.getSize() + 1;
        resultVO.setPageTotal(totalPage);

        resultVO.setList(findList.getRecords());

        resultVO.setPrePage(currentPage - 1L);

        long nextPage = currentPage + 1L;

        if (nextPage <= resultVO.getPageTotal()){
            resultVO.setNextPage(nextPage);
        }else {
            resultVO.setNextPage(0L);
        }

        return resultVO;
    }
}
