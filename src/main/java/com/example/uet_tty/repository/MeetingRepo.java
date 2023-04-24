package com.example.uet_tty.repository;

import com.example.uet_tty.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MeetingRepo extends JpaRepository<Meeting, Integer> {
    @Query("Select m from Meeting m where m.student_id=:x")
    ArrayList<Meeting> findAllByStudentId(@Param("x") int student_id);

    @Query("select m from Meeting m where m.expert_id=:x")
    ArrayList<Meeting> findAllByExpertId(@Param("x") int expert_id);

    @Query(value = "SELECT distinct e.expert_id from expert e inner join freetime f on e.expert_id=f.expert_id where dow = :dow" , nativeQuery = true)
    ArrayList<Integer> findAvailableExpertByDate(@Param("dow") int dow);
}
