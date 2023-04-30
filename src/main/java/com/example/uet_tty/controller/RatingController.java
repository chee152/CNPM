package com.example.uet_tty.controller;

import com.example.uet_tty.service.ExpertService;
import com.example.uet_tty.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RatingController {
    @Autowired
    RatingService ratingService;

    @Autowired
    ExpertService expertService;

    @GetMapping("/rating")
    public String rateExpert(@RequestParam("expert_id") int expert_id, Model model){
        String expert_name = expertService.getByExpertId(expert_id).getName();
        model.addAttribute("expert_id", expert_id);
        model.addAttribute("expert_name", expert_name);
        return "new-rating.html";
    }
    @PostMapping("/rating")
    public String saveRateExpert(@RequestParam("expert_id") int expert_id, @RequestParam("score") int score, @RequestParam(value = "message", required = false)String message){
        ratingService.newRating(score,expert_id,message);
        return "redirect:/meeting/history";
    }
}
