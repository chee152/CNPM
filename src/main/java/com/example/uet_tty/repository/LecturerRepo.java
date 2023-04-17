package com.example.uet_tty.repository;

import com.example.uet_tty.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepo extends JpaRepository<Lecturer,Integer> {
}
