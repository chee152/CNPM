package com.example.uet_tty.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meeting_id;

    private int expert_id;

    private int student_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @DateTimeFormat(pattern = "HH:mm")
    private Time time_start;
    @DateTimeFormat(pattern = "HH:mm")
    private Time time_end;

    //1 dang cho, 2 xac nhan, 3 khong chap nhan, 4 hoan thanh 5 huy
    private int status;

    private String note;
}
