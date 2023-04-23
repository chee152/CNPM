package com.example.uet_tty.controller;


import com.example.uet_tty.dto.FreetimeDTO;
import com.example.uet_tty.service.ExpertService;
import com.example.uet_tty.service.FreetimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/freetime")
@Controller
public class FreetimeController {
    @Autowired
    ExpertService expertService;
    @Autowired
    FreetimeService freetimeService;
    @GetMapping("/new")
    public String newFreetime() {
        return "new-freetime.html";
    }

    @PostMapping("/new")
    public String freetimeDow(@ModelAttribute FreetimeDTO freetimeDto, HttpSession session){
        freetimeDto.setExpert_id(expertService.getIdByUserId((int) session.getAttribute("user_id")));
        System.out.println(freetimeDto.getTime_start());
        System.out.println(freetimeDto.getTime_end());
        System.out.println(freetimeDto.getExpert_id());
        freetimeService.create(freetimeDto);
        return "redirect:/freetime/list";
    }
    @GetMapping("/list")
    public String getList(Model model, HttpSession session){
        List<FreetimeDTO> freetimeDTOList = freetimeService.getAllByExpertId(expertService.getIdByUserId((int) session.getAttribute("user_id")));
        model.addAttribute("freetime_list",freetimeDTOList);
        return "freetime-list.html";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int freetime_id){
        freetimeService.delete(freetime_id);
        return "redirect:/freetime/list";
    }

}
