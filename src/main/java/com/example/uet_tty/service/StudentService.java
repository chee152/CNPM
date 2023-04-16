package com.example.uet_tty.service;

import com.example.uet_tty.entity.Student;
import com.example.uet_tty.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;
    @Transactional
    public void create(Student student){
        studentRepo.save(student);
    }
    @Transactional
    public void delete(int id){
        studentRepo.deleteById(id);
    }

    @Transactional
    public void
}
