package com.example.scheduler.service;

import com.example.scheduler.dto.DayContentsPostRequestDto;
import com.example.scheduler.model.DayContents;
import com.example.scheduler.repository.DayContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DayContentsService {


    private final DayContentsRepository dayContentsRepository;

    private final MemberService memberService;

    public List<DayContents> getContents() {
        return dayContentsRepository.findAllByOrderByModifiedAtDesc();
    }

    public Optional<DayContents> getContent(Long id) {
        return dayContentsRepository.findById(id);
    }


    public String createContents(DayContentsPostRequestDto dayContentsPostRequestDto) {
        dayContentsPostRequestDto.setNickname(getNickname());
        DayContents dayContents = new DayContents(dayContentsPostRequestDto);
        dayContentsRepository.save(dayContents);
        return "등록완료";
    }


    @Transactional
    public String updateContents(DayContentsPostRequestDto dayContentsPostRequestDto, Long id) {
        DayContents dayContents = dayContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if (dayContents.getNickname().equals(getNickname())) {
            String dayContent = dayContentsPostRequestDto.getContents();
            dayContents.setContents(dayContent);
            dayContentsRepository.save(dayContents);
            msg = "수정완료";
        } else {
            msg = "자신의 게시물만 수정 가능합니다.";
        }
        return msg;
    }

    public String deleteContents(Long id) {
        DayContents dayContents = dayContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if (dayContents.getNickname().equals(getNickname())) {
            dayContentsRepository.deleteById(id);
            msg = "삭제완료";
        } else {
            msg = "자신의 게시물만 삭제 가능합니다.";
        }
        return msg;
    }

    private String getNickname() {
        return memberService.getMyInfo().getNickname();
    }
}