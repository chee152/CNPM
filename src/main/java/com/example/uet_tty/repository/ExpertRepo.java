package com.example.uet_tty.repository;

import com.example.uet_tty.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepo extends JpaRepository<Expert, Integer> {
}
