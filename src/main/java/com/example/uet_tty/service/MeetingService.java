package com.example.uet_tty.service;

import com.example.uet_tty.dto.*;
import com.example.uet_tty.entity.Expert;
import com.example.uet_tty.entity.Freetime;
import com.example.uet_tty.entity.Meeting;
import com.example.uet_tty.repository.ExpertRepo;
import com.example.uet_tty.repository.FreetimeRepo;
import com.example.uet_tty.repository.MeetingRepo;
import com.example.uet_tty.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Autowired
    StudentRepo studentRepo;

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
            break;
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
    public List<FreetimeDTO> getFreetimeByExpertIdAndDate(int expert_id, String date){
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
                break;
            case 5: dow = 4;
                break;
            case 6: dow = 5;
                break;
            case 7: dow = 6;
                break;
        }
        ArrayList<Freetime> freetimes = freetimeRepo.findByDateAndExpert_id(dow, expert_id);
        ArrayList<FreetimeDTO> freetimeDTOS = new ArrayList<>();
        for(Freetime f: freetimes){
            FreetimeDTO freetimeDTO = new FreetimeDTO();
            freetimeDTO.setFreetime_id(f.getFreetime_id());
            freetimeDTO.setDow(f.getDow());
            freetimeDTO.setTime_start(f.getTime_start().toString());
            freetimeDTO.setExpert_id(f.getExpert_id());
            freetimeDTO.setTime_end(f.getTime_end().toString());
            freetimeDTOS.add(freetimeDTO);
        }
        return freetimeDTOS;
    }

    public Time convertStringToTime(String time){
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time time1 = null;
        try {
             time1 = new Time(formatter.parse(time).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time1;
    }


    public boolean checkAvailable(int id, String date, String time){
        List<FreetimeDTO> freetimeDTOS = getFreetimeByExpertIdAndDate(id,date);
        Time timeCheck = convertStringToTime(time);
        boolean check1 = false;
        for(FreetimeDTO f: freetimeDTOS){
            if(convertStringToTime(f.getTime_start()).before(timeCheck)&&convertStringToTime(f.getTime_end()).after(timeCheck)){
                check1=true;
            }
        }
        List<Meeting> meetings = meetingRepo.findAllByExpert_idAndDate(id, date);
        System.out.println(meetings);
        boolean check2 = true;
        for(Meeting m: meetings){
            if(m.getTime_start().before(timeCheck)&&m.getTime_end().after(timeCheck)){
                check2=false;
            }
        }
        return (check1&&check2);
    }
    public void createNewMeetingRequest(int expert_id, String date, String time, String note, int user_id){
        Meeting meeting = new Meeting();
        meeting.setExpert_id(expert_id);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = formatter.parse(date);
            meeting.setDate(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        meeting.setStudent_id(studentRepo.findByUserId(user_id).getStudent_id());
        meeting.setNote(note);
        meeting.setStatus(1);
        meeting.setTime_start(convertStringToTime(time));
        Time endtime= convertStringToTime(time);
               endtime.setHours(endtime.getHours()+1);

        meeting.setTime_end(endtime);
        meetingRepo.save(meeting);
    }
    public List <ListMeetingDTO> getIncomingMeeting(int student_id){
       String current_date= LocalDate.now().toString();
        ArrayList<Meeting> incoming;
        incoming = meetingRepo.findAllByDateAfterAndStudentId(current_date, student_id);
        ArrayList<ListMeetingDTO> list = new ArrayList<>();
        for(Meeting i:incoming){
            ListMeetingDTO meetingDTO = new ListMeetingDTO();
            meetingDTO.setMeeting_id(i.getMeeting_id());
            meetingDTO.setStatus(i.getStatus());
            meetingDTO.setNote(i.getNote());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(i.getDate());
            meetingDTO.setDate(date);
            String expert_name = Objects.requireNonNull(expertRepo.findById(i.getExpert_id()).orElse(null)).getName();
            meetingDTO.setExpert_name(expert_name);
            meetingDTO.setExpert_id(i.getExpert_id());
            meetingDTO.setStudent_id(i.getStudent_id());
            meetingDTO.setTime_end(i.getTime_end().toString());
            meetingDTO.setTime_start(i.getTime_start().toString());
            String student_name=studentRepo.findByStudentId(i.getStudent_id()).getName();
            meetingDTO.setStudent_name(student_name);
            String expert_email = Objects.requireNonNull(expertRepo.findById(i.getExpert_id()).orElse(null)).getEmail();
            String expert_phone = Objects.requireNonNull(Objects.requireNonNull(expertRepo.findById(i.getExpert_id()).orElse(null)).getPhone());
            String student_email = studentRepo.findByStudentId(i.getStudent_id()).getEmail();
            String student_phone = studentRepo.findByStudentId(i.getStudent_id()).getPhone();
            meetingDTO.setExpert_email(expert_email);
            meetingDTO.setExpert_phone(expert_phone);
            meetingDTO.setStudent_email(student_email);
            meetingDTO.setStudent_phone(student_phone);
            list.add(meetingDTO);
        }
        return list;
    }
    public void cancel(int meeting_id){
        Meeting meeting =meetingRepo.findById(meeting_id).orElse(null);
        meeting.setStatus(3);
        meetingRepo.save(meeting);

    }
//    public List<ListMeetingDTO> getMeetingHistory(int student_id){
//
//    }

    public void updateStatus(){
        String current_date= LocalDate.now().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
        LocalTime time = LocalTime.now();
        String current_time= time.format(formatter);
        List<Meeting> MeetingList = meetingRepo.findAllMeetingBefore(current_date);
        for(Meeting m: MeetingList){
            if(m.getStatus()==1){
                m.setStatus(3);
            }else if(m.getStatus()==2){
                m.setStatus(4);
            }
            meetingRepo.save(m);
        }

    }
    public List<ListMeetingDTO> getMeetingHistory(int student_id){
        ArrayList<Meeting> meetingList= meetingRepo.findAllByStudentId(student_id);
        ArrayList<ListMeetingDTO> list = new ArrayList<>();
        for(Meeting i:meetingList){
            ListMeetingDTO meetingDTO = new ListMeetingDTO();
            meetingDTO.setMeeting_id(i.getMeeting_id());
            meetingDTO.setStatus(i.getStatus());
            meetingDTO.setNote(i.getNote());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(i.getDate());
            meetingDTO.setDate(date);
            String expert_name = Objects.requireNonNull(expertRepo.findById(i.getExpert_id()).orElse(null)).getName();
            meetingDTO.setExpert_name(expert_name);
            meetingDTO.setExpert_id(i.getExpert_id());
            meetingDTO.setStudent_id(i.getStudent_id());
            meetingDTO.setTime_end(i.getTime_end().toString());
            meetingDTO.setTime_start(i.getTime_start().toString());
            String student_name=studentRepo.findByStudentId(i.getStudent_id()).getName();
            meetingDTO.setStudent_name(student_name);
            String expert_email = Objects.requireNonNull(expertRepo.findById(i.getExpert_id()).orElse(null)).getEmail();
            String expert_phone = Objects.requireNonNull(Objects.requireNonNull(expertRepo.findById(i.getExpert_id()).orElse(null)).getPhone());
            String student_email = studentRepo.findByStudentId(i.getStudent_id()).getEmail();
            String student_phone = studentRepo.findByStudentId(i.getStudent_id()).getPhone();
            meetingDTO.setExpert_email(expert_email);
            meetingDTO.setExpert_phone(expert_phone);
            meetingDTO.setStudent_email(student_email);
            meetingDTO.setStudent_phone(student_phone);
            list.add(meetingDTO);
        }
        return list;
    }
    public List<ListMeetingDTO> getMeetingHistoryExpert(int expert_id) {
        ArrayList<Meeting> meetingList = meetingRepo.findAllByExpertId(expert_id);
        ArrayList<ListMeetingDTO> list = new ArrayList<>();
        for (Meeting i : meetingList) {
            ListMeetingDTO meetingDTO = new ListMeetingDTO();
            meetingDTO.setMeeting_id(i.getMeeting_id());
            meetingDTO.setStatus(i.getStatus());
            meetingDTO.setNote(i.getNote());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(i.getDate());
            meetingDTO.setDate(date);
            String expert_name = Objects.requireNonNull(expertRepo.findById(i.getExpert_id()).orElse(null)).getName();
            meetingDTO.setExpert_name(expert_name);
            meetingDTO.setExpert_id(i.getExpert_id());
            meetingDTO.setStudent_id(i.getStudent_id());
            meetingDTO.setTime_end(i.getTime_end().toString());
            meetingDTO.setTime_start(i.getTime_start().toString());
            String student_name = studentRepo.findByStudentId(i.getStudent_id()).getName();
            meetingDTO.setStudent_name(student_name);
            String expert_email = Objects.requireNonNull(expertRepo.findById(i.getExpert_id()).orElse(null)).getEmail();
            String expert_phone = Objects.requireNonNull(Objects.requireNonNull(expertRepo.findById(i.getExpert_id()).orElse(null)).getPhone());
            String student_email = studentRepo.findByStudentId(i.getStudent_id()).getEmail();
            String student_phone = studentRepo.findByStudentId(i.getStudent_id()).getPhone();
            meetingDTO.setExpert_email(expert_email);
            meetingDTO.setExpert_phone(expert_phone);
            meetingDTO.setStudent_email(student_email);
            meetingDTO.setStudent_phone(student_phone);
            list.add(meetingDTO);
        }
        return list;
    }
    public void accept(int meeting_id){
        Meeting meeting = meetingRepo.findById(meeting_id).orElse(null);
        if(meeting!=null){
            meeting.setStatus(2);
        }
        assert meeting != null;
        meetingRepo.save(meeting);
    }
    public void updateNote(int meeting_id, String note){
        Meeting meeting = meetingRepo.findById(meeting_id).orElse(null);

        if (meeting != null) {
            meeting.setNote(note);
            meetingRepo.save(meeting);
        }
    }
}
