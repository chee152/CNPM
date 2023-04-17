package com.example.uet_tty.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lecture_id;

    //yeu cau not null
    @Column(unique = true)
    private int user_id;

    private String name;

    private int age;

    private String department;

    private String introduction;

    private String email;

}

