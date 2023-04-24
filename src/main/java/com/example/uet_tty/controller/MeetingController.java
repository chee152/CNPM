package com.example.uet_tty.controller;

import com.example.uet_tty.dto.AvailableExpertDTO;
import com.example.uet_tty.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    MeetingService meetingService;


    @GetMapping("/new")
    public String newMeeting(){
        return "new-meeting.html";
    }
    @GetMapping("/search")
    public String newMeeting(@RequestParam("date") String date, Model model){
       List<AvailableExpertDTO> list = meetingService.list(date);
       model.addAttribute("list", list);
       return "available-expert.html";
    }
    @GetMapping("/request")
    public String newRequest(@RequestParam("id") int expert_id){
        //TODO: trả về trang điền giờ mong muốn, check xem có oke không
        return //TODO
    }
}
