package com.example.uet_tty.dto;

import lombok.Data;

@Data
public class FreetimeDTO {
    private int freetime_id;
    private int expert_id;

    //bat dau tu thu 2 la 1 den chu nhat la 7
    private int dow;


    private String time_start;

    private String time_end;
}
