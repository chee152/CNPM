package com.example.uet_tty.controller;

import com.example.uet_tty.dto.AvailableExpertDTO;
import com.example.uet_tty.dto.ListMeetingDTO;
import com.example.uet_tty.entity.Meeting;
import com.example.uet_tty.repository.MeetingRepo;
import com.example.uet_tty.repository.StudentRepo;
import com.example.uet_tty.service.ExpertService;
import com.example.uet_tty.service.MeetingService;
import com.example.uet_tty.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    MeetingService meetingService;

    @Autowired
    ExpertService expertService;

    @Autowired
    StudentService studentService;

    @GetMapping("/new")
    public String newMeeting(Model model){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String min = dtf.format(now);
        model.addAttribute("min", min);
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
    @PostMapping("/request")
    public String newRequestCheck(@RequestParam("id") int expert_id, @RequestParam("date") String date,
                                  @RequestParam(value="note", required=false) String note, @RequestParam("time") String time, Model model, HttpSession session){
            if(meetingService.checkAvailable(expert_id, date, time)){
                meetingService.createNewMeetingRequest(expert_id, date, time, note, (int) session.getAttribute("user_id"));
                return "redirect:/meeting/incoming";
            }else{
                String msg = "Xin lỗi, thời gian đã chọn chuyên gia không thể tiếp nhận, vui lòng chọn khoảng thời gian khác";
                model.addAttribute("expert", expertService.getByExpertId(expert_id));
                model.addAttribute("freetimeList", meetingService.getFreetimeByExpertIdAndDate(expert_id, date));
                model.addAttribute("date", date);
                model.addAttribute("msg",msg);
                return "new-request.html";
            }
    }
    @GetMapping("/incoming")
    public String getIncomingMeeting(Model model, HttpSession session){
        List<ListMeetingDTO> list=
                meetingService.getIncomingMeeting(studentService.searchByUserId((int) session.getAttribute("user_id")).getStudent_id());
        meetingService.updateStatus();
        model.addAttribute("list", list);
        return "incoming-meeting-student.html";
    }

    @GetMapping("/cancel")
    public String cancelMeeting(@RequestParam("id") int meeting_id){
        meetingService.cancel(meeting_id);
        return "redirect:/meeting/incoming";
    }
    //TODO viet them lich su meeting
    //TODO viet cho bac si: hien thi request, incoming
    //TODO cho phep chap nhan hoac huy
    //TODO viet them lich su meeting cho bac si
    //TODO cho phep bac si nhan da hoan thanh buoi kham
    @GetMapping("/history")
    public String history(Model model, HttpSession session){
        List<ListMeetingDTO> list =
        meetingService.getMeetingHistory(studentService.searchByUserId((int) session.getAttribute("user_id")).getStudent_id());
        model.addAttribute("list", list);
        return "meeting-history-student.html";
    }
}
