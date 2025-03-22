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
    public void add(Student student) {
        studentMapper.insertStudent(student);
    }

    @Override
    public void delete(String id){
        studentMapper.deleteStudentById(id);
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
//        引入pageStudent类中的数据，分别赋值给Integer类型的两个变量值，获取分页参数
        Integer currentPage = pageStudent.getCurrent();
        Integer pageSize = pageStudent.getPageSize();

//        根据请求参数创建分页对象，请求参数就是括号里的内容（当前页，每一页的数据个数）Page<Student>的Page是MPLUS框架提供的工具类，Student就是所要查询的表
        Page<Student> page = new Page<>(currentPage, pageSize);

//        创建查询条件，相对于上面那条的创建对象，为什么这个括号里面没有东西
        QueryWrapper<Student> selectQuery = new QueryWrapper<>();

//        自己创建查询条件，原本是输入页码和数量，不再输入数量，而是条件，搜索该页的满足条件的数据，id不是null时，需要添加id查询条件
        if (pageStudent.getId() != null){
//          这一句就是说明，如果id不为空，则输入id=？
            selectQuery.eq("id",pageStudent.getId());
        }
        else {
            return null;
        }
//      selectPage 是Base Mapper里的,IPage是Mybatis Plus 里自带的工具类，不需要自己创建，直接使用即可
        IPage<Student> findList = studentMapper.selectPage(page, selectQuery);

        return createResultV0(findList, currentPage);
    }

    public BasicPageResultVO<Student> createResultV0(IPage<Student> findList, Integer currentPage) {
        BasicPageResultVO<Student> resultVO = new BasicPageResultVO<>();
//      set是干什么的
        resultVO.setCurrent(findList.getCurrent());
        resultVO.setPageSize(findList.getSize());
        resultVO.setTotal(findList.getTotal());
//      如果某一页的数据数量达不到所设置的pageSize，这里是条件判断语句吗
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
