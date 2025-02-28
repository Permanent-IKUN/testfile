package com.example.team_projectdemo02.model;

import lombok.Data;

import java.util.List;

@Data
public class BasicPageResultVO<student> {
    Long current = 1L;

    Long pageSize = 20L;

    Long total;

    Long PageTotal;

    Long nextPage;

    Long prePage;

    List<student> List;

}
