package com.example.scheduler.service;

import com.example.scheduler.repository.DayContentsRepository;
import com.example.scheduler.dto.DayContentsPostRequestDto;
import com.example.scheduler.model.DayContents;
import com.example.scheduler.repository.DayContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DayContentsService {

    private final DayContentsRepository dayContentsRepository;

    @Autowired
    public DayContentsService(DayContentsRepository dayContentsRepository) {
        this.dayContentsRepository = dayContentsRepository;
    }

    public List<DayContents> getContents(){
        return dayContentsRepository.findAllByOrderByModifiedAtDesc();
    }

    public Optional<DayContents> getContent(Long id){
        return dayContentsRepository.findById(id);
    }


    public String createContents(DayContentsPostRequestDto dayContentsPostRequestDto, UserDetails userDetails) {
        DayContents dayContents = new DayContents(dayContentsPostRequestDto, userDetails);
        dayContentsRepository.save(dayContents);
        return "등록완료";
    }

    @Transactional
    public String updateContents(DayContentsPostRequestDto dayContentsPostRequestDto, Long id, UserDetails userDetails) {
        DayContents dayContents = dayContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if(dayContents.getUsername().equals(userDetails.getUsername())){
            String dayContent = dayContentsPostRequestDto.getContents();
            dayContents.setContents(dayContent);
            dayContentsRepository.save(dayContents);
            msg = "수정완료";
        }else{
            msg = "자신의 게시물만 수정 가능합니다.";
        }
        return msg;
    }

    public String deleteContents(Long id, UserDetails userDetails) {
        DayContents dayContents = dayContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if(dayContents.getUsername().equals(userDetails.getUsername())){
            dayContentsRepository.deleteById(id);
            msg = "삭제완료";
        }else{
            msg = "자신의 게시물만 삭제 가능합니다.";
        }
        return msg;
    }
}