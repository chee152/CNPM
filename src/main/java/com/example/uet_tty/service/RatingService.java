package com.example.uet_tty.service;

import com.example.uet_tty.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    RatingRepo ratingRepo;
    public void newRating(int score, int expert_id){

    }
    public Double getExpertRating(int expert_id){
        if(ratingRepo.getExpertRatingById(expert_id)==null){
            return 0.0;
        }
         return ratingRepo.getExpertRatingById(expert_id);
    }
}
