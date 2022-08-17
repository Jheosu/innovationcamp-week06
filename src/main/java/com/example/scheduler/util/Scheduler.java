package com.example.scheduler.util;

import com.example.scheduler.dto.DayWeekRequestDto;
import com.example.scheduler.model.DayContents;
import com.example.scheduler.model.Member;
import com.example.scheduler.model.WeekContents;
import com.example.scheduler.repository.DayContentsRepository;
import com.example.scheduler.repository.MemberRepository;
import com.example.scheduler.repository.WeekContentsRepository;
import com.example.scheduler.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class Scheduler {
    private final WeekContentsRepository weekContentsRepository;
    private final DayContentsRepository dayContentsRepository;

    private final MemberRepository memberRepository;


    private final MemberService memberService;

    @Scheduled(cron = "0 0/2 * * * *")
    @Transactional
    public void deleteday() {
        dayContentsRepository.deleteAll();
        List<WeekContents> weekContentsList;

        if (getDayOfWeek() == 7) {
            weekContentsList = weekContentsRepository.findByDaynum(1);
        } else {
            weekContentsList = weekContentsRepository.findByDaynum(getDayOfWeek() + 1);
        }

        if (weekContentsList != null) {
            for (WeekContents weekContents : weekContentsList) {
                DayWeekRequestDto requestDto = DayWeekRequestDto.builder()
                        .title(weekContents.getTitle())
                        .contents(weekContents.getContents())
                        .nickname(weekContents.getNickname())
                        .daynum(weekContents.getDaynum())
                        .build();
                Member member = weekContents.getMember();
                DayContents dayContents = new DayContents(requestDto);
                dayContents.confirmPost(member);
                dayContentsRepository.save(dayContents);
            }
        }

        log.info("게시글이 삭제 되었습니다");
    }

    @Scheduled(cron = "0 0 0 * * 1") // 매주 월요일날 실행
    public void deleteweek() {
        weekContentsRepository.deleteAll();
        log.info("게시글이 삭제 되었습니다");
    }


    public int getDayOfWeek() {
        Calendar rightNow = Calendar.getInstance();
        int day_of_week = rightNow.get(Calendar.DAY_OF_WEEK);
        return day_of_week;
    }

}
