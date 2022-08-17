package com.example.scheduler.service;

import com.example.scheduler.dto.MonthContentsPostRequestDto;
import com.example.scheduler.model.Member;
import com.example.scheduler.model.MonthContents;
import com.example.scheduler.repository.MemberRepository;
import com.example.scheduler.repository.MonthContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonthContentsService {


    private final MonthContentsRepository monthContentsRepository;

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    public List<MonthContents> getContents() {
        return monthContentsRepository.findAllByOrderByModifiedAtDesc();
    }

    public Optional<MonthContents> getContent(Long id) {
        return monthContentsRepository.findById(id);
    }


    public String createContents(MonthContentsPostRequestDto monthContentsPostRequestDto) {
        monthContentsPostRequestDto.setNickname(getNickname());
        Member member = memberRepository.findById(memberService.getMyInfo().getId()).orElseThrow(() -> new RuntimeException("해당하는 유저가 없습니다"));


        MonthContents monthContents = new MonthContents(monthContentsPostRequestDto);
        monthContents.confirmPost(member);


        monthContentsRepository.save(monthContents);
        return "등록완료";
    }


    @Transactional
    public String updateContents(MonthContentsPostRequestDto monthContentsPostRequestDto, Long id) {
        MonthContents monthContents = monthContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if (monthContents.getNickname().equals(getNickname())) {
            String monthContent = monthContentsPostRequestDto.getContents();
            monthContents.update(monthContentsPostRequestDto);
            monthContentsRepository.save(monthContents);
            msg = "수정완료";
        } else {
            msg = "자신의 게시물만 수정 가능합니다.";
        }
        return msg;
    }

    public String deleteContents(Long id) {
        MonthContents monthContents = monthContentsRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다."));
        String msg;
        if (monthContents.getNickname().equals(getNickname())) {
            monthContentsRepository.deleteById(id);
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