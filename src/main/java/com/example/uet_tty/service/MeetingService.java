package com.example.uet_tty.service;

import com.example.uet_tty.dto.AvailableExpertDTO;
import com.example.uet_tty.dto.MeetingDTO;
import com.example.uet_tty.dto.TimeAvailable;
import com.example.uet_tty.entity.Expert;
import com.example.uet_tty.entity.Freetime;
import com.example.uet_tty.entity.Meeting;
import com.example.uet_tty.repository.ExpertRepo;
import com.example.uet_tty.repository.FreetimeRepo;
import com.example.uet_tty.repository.MeetingRepo;
import com.example.uet_tty.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MeetingService {
    @Autowired
    MeetingRepo meetingRepo;
    @Autowired
    FreetimeRepo freetimeRepo;

    @Autowired
    ExpertRepo expertRepo;

    @Autowired
    RatingService ratingService;
    //TODO findAllByStudentID
    //TODO findAllByExpertID
    //TODO findAvailableExpertByDate
    //TODO getListOf

    List<MeetingDTO> findAllByStudentId(int student_id){
        ArrayList<MeetingDTO> meetingDTOS = new ArrayList<>();
        ArrayList<Meeting> meetings = meetingRepo.findAllByStudentId(student_id);
        for(Meeting m:meetings){
            MeetingDTO meetingDTO = new MeetingDTO();
            meetingDTO.setStudent_id(m.getStudent_id());
            meetingDTO.setExpert_id(m.getExpert_id());
            meetingDTO.setDate(m.getDate().toString());
            meetingDTO.setTime_start(m.getTime_start().toString());
            meetingDTO.setTime_end(m.getTime_end().toString());
            meetingDTO.setNote(m.getNote());
            meetingDTO.setStatus(m.getStatus());
            meetingDTOS.add(meetingDTO);

        }
        return meetingDTOS;
    }

    List<MeetingDTO> findAllByExpertId(int expert_id){
        ArrayList<MeetingDTO> meetingDTOS = new ArrayList<>();
        ArrayList<Meeting> meetings = meetingRepo.findAllByExpertId(expert_id);
        for(Meeting m:meetings){
            MeetingDTO meetingDTO = new MeetingDTO();
            meetingDTO.setStudent_id(m.getStudent_id());
            meetingDTO.setExpert_id(m.getExpert_id());
            meetingDTO.setDate(m.getDate().toString());
            meetingDTO.setTime_start(m.getTime_start().toString());
            meetingDTO.setTime_end(m.getTime_end().toString());
            meetingDTO.setNote(m.getNote());
            meetingDTO.setStatus(m.getStatus());
            meetingDTOS.add(meetingDTO);

        }
        return meetingDTOS;
    }
    public List<AvailableExpertDTO> list(String date){
        ArrayList<AvailableExpertDTO> list = new ArrayList<>();
        int dow=1;
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            dow=cal.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (dow){
            case 1: dow = 7;
            break;
            case 2: dow =1;
            break;
            case 3: dow = 2;
            break;
            case 4: dow =3;
            case 5: dow = 4;
            break;
            case 6: dow = 5;
            break;
            case 7: dow = 6;
            break;
        }
        ArrayList<Integer> expert_id_list = meetingRepo.findAvailableExpertByDate(dow);
        System.out.println(expert_id_list);

        for(int i : expert_id_list){

            AvailableExpertDTO a = new AvailableExpertDTO();
            ArrayList<Freetime> freetimes = freetimeRepo.findByDateAndExpert_id(dow, i);
            Expert e = expertRepo.findById(i).orElse(null);
                List<TimeAvailable>start_end_time_list = new ArrayList<>();
                for(Freetime f:freetimes){
                    System.out.println(f);
                    TimeAvailable timeAvailable = new TimeAvailable(f.getTime_start().toString(), f.getTime_end().toString());
                    start_end_time_list.add(timeAvailable);
                }
                a.setExpert_id(e.getExpert_id());
                a.setAge(e.getAge());
                a.setEmail(e.getEmail());
                a.setFee(e.getFee());
                a.setName(e.getName());
                a.setPhone(e.getPhone());
                a.setRating(ratingService.getExpertRating(i));
//                a.setWorks_at(e.getWorks_at());
//                a.setIntroduction(e.getIntroduction());
                a.setTimeAvailable(start_end_time_list);
                list.add(a);
            }
        return list;
    }
}
