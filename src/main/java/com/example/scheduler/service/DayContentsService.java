package com.example.scheduler.service;

import com.example.scheduler.dto.DayContentsPostRequestDto;
import com.example.scheduler.dto.DayWeekRequestDto;
import com.example.scheduler.model.DayContents;
import com.example.scheduler.model.Member;
import com.example.scheduler.model.WeekContents;
import com.example.scheduler.repository.DayContentsRepository;
import com.example.scheduler.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DayContentsService {


    private final DayContentsRepository dayContentsRepository;

    private final MemberRepository memberRepository;

    private final MemberService memberService;

    public List<DayContents> getContents() {
        return dayContentsRepository.findByNickname(getNickname());
    }

    public Optional<DayContents> getContent(Long id) {
        return dayContentsRepository.findById(id);
    }


    public String createContents(DayWeekRequestDto requestDto) {
        requestDto.setNickname(getNickname());
        Member member = memberRepository.findById(memberService.getMyInfo().getId()).orElseThrow(() -> new RuntimeException("해당하는 유저가 없습니다"));
        requestDto.setDaynum(getDayOfWeek());
        DayContents dayContents = new DayContents(requestDto);
        dayContents.confirmPost(member);
        WeekContents weekContents = new WeekContents(requestDto);
        weekContents.confirmPost(member);

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
            dayContents.update(dayContentsPostRequestDto);
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

    public int getDayOfWeek() {
        Calendar rightNow = Calendar.getInstance();
        int day_of_week = rightNow.get(Calendar.DAY_OF_WEEK);
        return day_of_week;
    }

}