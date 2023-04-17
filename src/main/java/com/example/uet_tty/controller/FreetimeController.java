package com.example.uet_tty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class FreetimeController {
    @GetMapping("/freetime/new")
    public String freetimeDow(@RequestParam("dow[]") int[] dow, Model model){

        ArrayList<Integer> dowList = new ArrayList<Integer>();
        for(int i=0;i<dow.length;i++){
            dowList.add(dow[i]);
        }
        model.addAttribute("dowList",dowList);
        return "new-freetime-time.html";
    }

}
