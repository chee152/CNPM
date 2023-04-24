package com.example.uet_tty.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class AvailableExpertDTO {

    private int expert_id;



    private List<TimeAvailable> timeAvailable;
    private String name;

    private int age;

//    private String works_at;
//
//    private String introduction;

    private String email;

    private String phone;
    private double fee;
    private Double rating;
}
