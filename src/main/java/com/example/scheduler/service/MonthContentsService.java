package com.example.scheduler.service;

import com.example.scheduler.dto.MonthContentsPostRequestDto;
import com.example.scheduler.model.MonthContents;
import com.example.scheduler.model.DayContents;
import com.example.scheduler.repository.MonthContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MonthContentsService {

    private final MonthContentsRepository monthContentsRepository;

    @Autowired
    public MonthContentsService(MonthContentsRepository monthContentsRepository) {
        this.monthContentsRepository = monthContentsRepository;
    }

    public List<MonthContents> getContents(){
        return monthContentsRepository.findAllByOrderByModifiedAtDesc();
    }

    public Optional<MonthContents> getContent(Long id){
        return monthContentsRepository.findById(id);
    }


    public String createContents(MonthContentsPostRequestDto monthContentsPostRequestDto, UserDetails userDetails) {
        MonthContents monthContents = new MonthContents(monthContentsPostRequestDto, userDetails);
        monthContentsRepository.save(monthContents);
        return "등록완료";
    }

    @Transactional
    public String updateContents(MonthContentsPostRequestDto monthContentsPostRequestDto, Long id, UserDetails userDetails) {
        MonthContents monthContents = monthContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if(monthContents.getUsername().equals(userDetails.getUsername())){
            String monthContent = monthContentsPostRequestDto.getContents();
            monthContents.setContents(monthContent);
            monthContentsRepository.save(monthContents);
            msg = "수정완료";
        }else{
            msg = "자신의 게시물만 수정 가능합니다.";
        }
        return msg;
    }

    public String deleteContents(Long id, UserDetails userDetails) {
        MonthContents monthContents = monthContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if(monthContents.getUsername().equals(userDetails.getUsername())){
            monthContentsRepository.deleteById(id);
            msg = "삭제완료";
        }else{
            msg = "자신의 게시물만 삭제 가능합니다.";
        }
        return msg;
    }
}