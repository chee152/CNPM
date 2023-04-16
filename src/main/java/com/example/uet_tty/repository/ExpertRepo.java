package com.example.uet_tty.repository;

import com.example.uet_tty.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ExpertRepo extends JpaRepository<Expert, Integer> {
    @Query("SELECT e FROM Expert e WHERE e.name LIKE :x")
    ArrayList<Expert> searchByName(@Param("x") String name);


}
