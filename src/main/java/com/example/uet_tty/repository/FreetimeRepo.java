package com.example.uet_tty.repository;

import com.example.uet_tty.entity.Freetime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface FreetimeRepo extends JpaRepository<Freetime, Integer> {
    @Query("SELECT f FROM Freetime f WHERE f.expert_id= :x ORDER BY f.dow")
   ArrayList<Freetime> findByExpertId(@Param("x") int expert_id);


    @Query("SELECT f FROM Freetime f WHERE f.dow= :x ORDER BY f.time_start")
    ArrayList<Freetime> findByDow(@Param("x") int dow);

//    @Query("SELECT f FROM Freetime f WHERE f.dow= (weekday(:x)+1) ORDER BY f.time_start")
//    ArrayList<Freetime> findByDate(@Param("x") String date);

    @Query(value = "SELECT * from Freetime f where f.dow=:dow and f.expert_id= :id", nativeQuery = true)
    ArrayList<Freetime> findByDateAndExpert_id(@Param("dow") int dow,@Param("id") int id);
}
