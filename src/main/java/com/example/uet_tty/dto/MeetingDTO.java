package com.example.uet_tty.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class MeetingDTO {
    private int meeting_id;

    private int expert_id;

    private String student_id;

    private String date;

    private String time_start;
    private String time_end;

    //1 dang cho, 2 xac nhan, 3 khong chap nhan, 4 hoan thanh
    private int status;

    private String note;
}
