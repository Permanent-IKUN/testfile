package com.example.team_projectdemo02.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.print.DocFlavor;

@Data
@TableName(value = "student", schema = "demo")
public class Student {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
//  需要的校验条件：成绩不能为空、最多一位小数、0到150之间
    @NotNull(message = "语文成绩不能为空")
    @DecimalMin(value = "0",message = "语文成绩不能低于0")
    @DecimalMax(value = "150",message = "语文成绩不能超过150")
    Double chinese;
    @NotNull(message = "数学成绩不能为空")
    @DecimalMin(value = "0",message = "数学成绩不能低于0")
    @DecimalMax(value = "150",message = "数学成绩不能超过150")
    Double math;
    @NotNull(message = "英语成绩不能为空")
    @DecimalMin(value = "0",message = "英语成绩不能低于0")
    @DecimalMax(value = "150",message = "英语成绩不能超过150")
    Double english;

}
