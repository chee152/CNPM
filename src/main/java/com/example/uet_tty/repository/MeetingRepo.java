package com.example.uet_tty.repository;


import com.example.uet_tty.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface MeetingRepo extends JpaRepository<Meeting, Integer> {
    @Query("Select m from Meeting m where m.student_id=:x")
    ArrayList<Meeting> findAllByStudentId(@Param("x") int student_id);

    @Query("select m from Meeting m where m.expert_id=:x")
    ArrayList<Meeting> findAllByExpertId(@Param("x") int expert_id);

    @Query(value = "SELECT distinct e.expert_id from expert e inner join freetime f on e.expert_id=f.expert_id where dow = :dow" , nativeQuery = true)
    ArrayList<Integer> findAvailableExpertByDate(@Param("dow") int dow);

    @Query( value="select * from Meeting m where m.expert_id=:x and m.date=:y",nativeQuery = true)
    ArrayList<Meeting> findAllByExpert_idAndDate(@Param("x") int expert_id, @Param("y") String date);

    @Query(value = "select * from Meeting m where m.date>=:x and (m.status=1 or m.status=2) and student_id=:y",nativeQuery = true)
    ArrayList<Meeting> findAllByDateAfterAndStudentId(@Param("x") String date, @Param("y") int student_id);


    @Query(value="select * from Meeting m where m.date=:x and m.time_end<:y", nativeQuery=true)
    ArrayList<Meeting> findAllMeetingBefore(@Param("x") String current_date, @Param("y") String current_time);
}
