package com.example.team_projectdemo02.mapping;

import com.example.team_projectdemo02.entity.Student;
import lombok.Getter;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Insert("INSERT INTO student(id,name,chinese,math,english) VALUES (#{id},#{name},#{chinese},#{math},#{english})")
    void insertStudent(Student student);

    @Delete("DELETE FROM student WHERE id = #{id}")
    void deleteStudentById(String id);

    @Select("SELECT*FROM student WHERE id = #{id}")
    Student findById(String id);

    @Select("SELECT*FROM  student")
    List<Student> findAllStudents();

    @Update("UPDATE student SET name = #{name},chinese = #{chinese},math = #{math},english = #{english} WHERE id = #{id}")
    void update(Student student);
}
