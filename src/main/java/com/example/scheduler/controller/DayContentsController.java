package com.example.scheduler.controller;

import com.example.scheduler.dto.DayContentsPostRequestDto;
import com.example.scheduler.dto.DayWeekRequestDto;
import com.example.scheduler.model.DayContents;
import com.example.scheduler.model.Member;
import com.example.scheduler.repository.DayContentsRepository;
import com.example.scheduler.service.DayContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class DayContentsController {

    private final DayContentsService dayContentsService;
    private final DayContentsRepository dayContentsRepository;

    @GetMapping("/api/post/day")
    public List<DayContents> getContents() {
        return dayContentsService.getContents();
    }

    // 일정 조회
    @GetMapping("/api/post/day/{postId}")
    public Optional<DayContents> getContent(@PathVariable Long postId) {
        return dayContentsService.getContent(postId);
    }


    // 일정 생성
    @PostMapping("/api/post/day")
    public String createContents(@RequestBody DayWeekRequestDto requestDto) {
        System.out.println("포스팅 생성");
        return dayContentsService.createContents(requestDto);
    }

    // 일정 수정
    @PutMapping("/api/post/day/{postId}")
    public String updateCotents(@PathVariable Long postId, @RequestBody DayContentsPostRequestDto dayContentsPostRequestDto) {

        System.out.println(dayContentsPostRequestDto.getContents());
        return dayContentsService.updateContents(dayContentsPostRequestDto, postId);
    }

    //일정 삭제
    @DeleteMapping("/api/post/day/{postId}")
    public String deleteContents(@PathVariable Long postId, @AuthenticationPrincipal Member member) {
        return dayContentsService.deleteContents(postId);

    }

}