package com.example.team_projectdemo02.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import javax.print.DocFlavor;

@Data
@Entity
public class Student {
    @Id
    String id;
    String name;
    String chinese;
    String math;
    String english;

}
