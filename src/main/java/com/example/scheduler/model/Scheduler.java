package com.example.scheduler.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class Scheduler {


    private final DayPostingRepository dayPostingRepository;

    // 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 1 * * *")
    public void deleteNoComment() {

        List<DayPosting> dayPostings = dayPostingRepository.findAllByCntComment(0L);

        for (DayPosting dayPosting : dayPostings) {
            dayPostingRepository.deleteById(dayPosting.getPostingId());
            log.info("게시물 <" + dayPosting.getTitle() + ">이 삭제되었습니다.");
        }


    }
}