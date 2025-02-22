package com.example.team_projectdemo02.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import javax.print.DocFlavor;

@Data
@TableName(value = "student", schema = "test")
public class Student {
    @TableId(type = IdType.AUTO)
    String id;
    String name;
    String chinese;
    String math;
    String english;

}
