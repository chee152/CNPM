package com.example.uet_tty.controller;

import com.example.uet_tty.dto.AvailableExpertDTO;
import com.example.uet_tty.service.ExpertService;
import com.example.uet_tty.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    MeetingService meetingService;

    @Autowired
    ExpertService expertService;

    @GetMapping("/new")
    public String newMeeting(){
        return "new-meeting.html";
    }
    @GetMapping("/search")
    public String newMeeting(@RequestParam("date") String date, Model model){
       List<AvailableExpertDTO> list = meetingService.list(date);
       model.addAttribute("list", list);
       model.addAttribute("date", date);
       return "available-expert.html";
    }
    @GetMapping("/request")
    public String newRequest(@RequestParam("id") int expert_id, @RequestParam("date") String date, Model model){

        model.addAttribute("expert", expertService.getByExpertId(expert_id));
        model.addAttribute("freetimeList", meetingService.getFreetimeByExpertIdAndDate(expert_id, date));
        model.addAttribute("date", date);
        return "new-request.html";
    }
//    @PostMapping("/request")
//    public String newRequestCheck(@RequestParam("id") int expert_id, @RequestParam("date") String date,
//                                  @RequestParam("note") String note, @RequestParam("time") String time, Model model){
//            if(meetingService.checkAvailable(expert_id, date, time)){
//
//            }else{
//                String msg = "Xin lỗi, thời gian đã chọn chuyên gia không thể tiếp nhận, vui lòng chọn khoảng thời gian khác";
//                model.addAttribute("msg",msg);
//                return "new-request.html";
//            }
//    }
}
