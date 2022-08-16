package com.example.scheduler.service;

import com.example.scheduler.dto.WeekContentsPostRequestDto;
import com.example.scheduler.model.WeekContents;
import com.example.scheduler.repository.WeekContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeekContentsService {


    private final WeekContentsRepository weekContentsRepository;

    private final MemberService memberService;

    public List<WeekContents> getContents() {
        return weekContentsRepository.findAllByOrderByModifiedAtDesc();
    }

    public Optional<WeekContents> getContent(Long id) {
        return weekContentsRepository.findById(id);
    }


    public String createContents(WeekContentsPostRequestDto weekContentsPostRequestDto) {
        weekContentsPostRequestDto.setNickname(getNickname());
        WeekContents weekContents = new WeekContents(weekContentsPostRequestDto);
        weekContentsRepository.save(weekContents);
        return "등록완료";
    }


    @Transactional
    public String updateContents(WeekContentsPostRequestDto weekContentsPostRequestDto, Long id) {
        WeekContents weekContents = weekContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if (weekContents.getNickname().equals(getNickname())) {
            String weekContent = weekContentsPostRequestDto.getContents();
            weekContents.setContents(weekContent);
            weekContentsRepository.save(weekContents);
            msg = "수정완료";
        } else {
            msg = "자신의 게시물만 수정 가능합니다.";
        }
        return msg;
    }

    public String deleteContents(Long id) {
        WeekContents weekContents = weekContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if (weekContents.getNickname().equals(getNickname())) {
            weekContentsRepository.deleteById(id);
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