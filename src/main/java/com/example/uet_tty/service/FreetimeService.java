package com.example.uet_tty.service;

import com.example.uet_tty.dto.FreetimeDTO;
import com.example.uet_tty.entity.Freetime;
import com.example.uet_tty.repository.FreetimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class FreetimeService {
    @Autowired
    FreetimeRepo freetimeRepo;
    public void create(FreetimeDTO freetimeDto) {
        Freetime freetime = new Freetime();
        freetime.setExpert_id(freetimeDto.getExpert_id());
        freetime.setDow(freetimeDto.getDow());
        DateFormat formatter = new SimpleDateFormat("HH:mm");

        try {
            freetime.setTime_start(new Time(formatter.parse(freetimeDto.getTime_start()).getTime()));
            freetime.setTime_end(new Time(formatter.parse(freetimeDto.getTime_end()).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        freetimeRepo.save(freetime);
    }
    public List<FreetimeDTO> getAllByExpertId(int id){
        List<Freetime> freetimes = freetimeRepo.findByExpertId(id);
        List<FreetimeDTO> freetimeDTOList = new ArrayList<>();
        for(Freetime f:freetimes){
            FreetimeDTO freetimeDTO = new FreetimeDTO();
            freetimeDTO.setFreetime_id(f.getFreetime_id());
            freetimeDTO.setDow(f.getDow());
            freetimeDTO.setExpert_id(f.getExpert_id());
            freetimeDTO.setTime_start(f.getTime_start().toString());
            freetimeDTO.setTime_end(f.getTime_end().toString());
            freetimeDTOList.add(freetimeDTO);
        }
        return freetimeDTOList;
    }
    public FreetimeDTO getByFreetimeId(int id){
        Freetime f = freetimeRepo.findById(id).orElse(null);
        FreetimeDTO freetimeDTO = new FreetimeDTO();

        if(f!=null) {
            freetimeDTO.setFreetime_id(f.getFreetime_id());
            freetimeDTO.setDow(f.getDow());
            freetimeDTO.setExpert_id(f.getExpert_id());
            freetimeDTO.setTime_start(f.getTime_start().toString());
            freetimeDTO.setTime_end(f.getTime_end().toString());
        }
        return freetimeDTO;
    }
    public void delete(int id){
        freetimeRepo.deleteById(id);
    }
}
