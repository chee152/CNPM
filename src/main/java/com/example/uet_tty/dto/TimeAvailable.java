package com.example.uet_tty.dto;

import lombok.Data;

@Data
public class TimeAvailable {
    private String time_start;
    private String time_end;
    public TimeAvailable(String time_start, String time_end){
        this.time_end=time_end;
        this.time_start=time_start;
    }
}
