package com.example.scheduler.service;

import com.example.scheduler.dto.WeekContentsPostRequestDto;
import com.example.scheduler.model.WeekContents;
import com.example.scheduler.repository.WeekContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WeekContentsService {

    private final WeekContentsRepository weekContentsRepository;

    @Autowired
    public WeekContentsService(WeekContentsRepository weekContentsRepository) {
        this.weekContentsRepository = weekContentsRepository;
    }

    public List<WeekContents> getContents(){
        return weekContentsRepository.findAllByOrderByModifiedAtDesc();
    }

    public Optional<WeekContents> getContent(Long id){
        return weekContentsRepository.findById(id);
    }


    public String createContents(WeekContentsPostRequestDto weekContentsPostRequestDto, UserDetails userDetails) {
        WeekContents weekContents = new WeekContents(weekContentsPostRequestDto, userDetails);
        weekContentsRepository.save(weekContents);
        return "등록완료";
    }

    @Transactional
    public String updateContents(WeekContentsPostRequestDto weekContentsPostRequestDto, Long id, UserDetails userDetails) {
        WeekContents weekContents = weekContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if(weekContents.getUsername().equals(userDetails.getUsername())){
            String weekContent = weekContentsPostRequestDto.getContents();
            weekContents.setContents(weekContent);
            weekContentsRepository.save(weekContents);
            msg = "수정완료";
        }else{
            msg = "자신의 게시물만 수정 가능합니다.";
        }
        return msg;
    }

    public String deleteContents(Long id, UserDetails userDetails) {
        WeekContents weekContents = weekContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if(weekContents.getUsername().equals(userDetails.getUsername())){
            weekContentsRepository.deleteById(id);
            msg = "삭제완료";
        }else{
            msg = "자신의 게시물만 삭제 가능합니다.";
        }
        return msg;
    }
}