package com.example.uet_tty.repository;

import com.example.uet_tty.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.student_id= :x")
     Student findByStudentId(@Param("x") int id);

    @Query("select s from Student s where s.user_id=:x")
    Student findByUserId(@Param("x") int id);
}
