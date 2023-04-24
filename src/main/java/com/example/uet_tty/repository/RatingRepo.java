package com.example.uet_tty.repository;

import com.example.uet_tty.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepo extends JpaRepository<Rating, Integer> {
    @Query("select avg(r.score) from Rating r where r.expert_id  = :x")
    Double getExpertRatingById(@Param("x") int expert_id);
}
