package com.example.team_projectdemo02.model;

import lombok.Data;

import java.util.List;

@Data

//分页类
public class BasicPageResultVO<T> {
    //当前页
    Long current = 1L;
    //每一页有多少条
    Long pageSize = 20L;

    //一共有多少条
    Long total;

    //一共有多少页
    Long PageTotal;

    Long nextPage;

    Long prePage;

    List<T> List;

}
