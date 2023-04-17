package com.example.uet_tty.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@Data
@Entity
public class Freetime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int freetime_id;

    private int expert_id;

    //bat dau tu thu 2 la 1 den chu nhat la 7
    private int dow;

    private Time time;
}
