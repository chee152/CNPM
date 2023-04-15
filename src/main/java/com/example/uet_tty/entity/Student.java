package com.example.uet_tty.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Student {
    @Id
    private String student_id;

    private int user_id;
    private String name;
    private int age;
    private String address;
    private String department;
    private String email;
}
