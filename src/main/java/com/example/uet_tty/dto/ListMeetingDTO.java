package com.example.uet_tty.dto;

import lombok.Data;

@Data
public class ListMeetingDTO {
    private int meeting_id;

    private String expert_name;

    private int expert_id;

    private String student_name;

    private int student_id;

    private String date;

    private String time_start;
    private String time_end;

    //1 dang cho, 2 xac nhan, 3 khong chap nhan, 4 hoan thanh
    private int status;

    private String note;
    private String expert_email;
    private String expert_phone;
    private String student_email;
    private String student_phone;
}
