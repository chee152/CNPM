package com.example.uet_tty.repository;

import com.example.uet_tty.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepo extends JpaRepository<Meeting, Integer> {
}
