package com.example.uet_tty.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expert_id;

    //yeu cau not null
    @Column(unique = true)
    private int user_id;

    private String name;

    private int age;

    private String works_at;

    private String introduction;

    private String email;

    private String phone;
    private double fee;
}
