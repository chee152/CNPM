package com.example.uet_tty.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meeting_id;

    private int expert_id;

    private String student_id;

    private Date date;

    private Time time;

    //1 dang cho, 2 xac nhan, 3 khong chap nhan, 4 hoan thanh
    private int status;

    private String note;
}
