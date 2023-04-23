package com.example.uet_tty.repository;

import com.example.uet_tty.entity.Freetime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface FreetimeRepo extends JpaRepository<Freetime, Integer> {
    @Query("SELECT f FROM Freetime f WHERE f.expert_id= :x ORDER BY f.dow")
   ArrayList<Freetime> findByExpertId(@Param("x") int expert_id);


    @Query("SELECT f FROM Freetime f WHERE f.dow= :x ORDER BY f.time_start")
    ArrayList<Freetime> findByDow(@Param("x") int dow);



}
