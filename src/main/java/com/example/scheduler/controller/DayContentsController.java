package com.example.scheduler.controller;

import com.example.scheduler.dto.DayContentsPostRequestDto;
import com.example.scheduler.model.DayContents;
import com.example.scheduler.model.Member;
import com.example.scheduler.repository.DayContentsRepository;
import com.example.scheduler.service.DayContentsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class DayContentsController {

    private final DayContentsService dayContentsService;
    private final DayContentsRepository dayContentsRepository;

    public DayContentsController(DayContentsService dayContentsService, DayContentsRepository dayContentsRepository) {
        this.dayContentsService = dayContentsService;
        this.dayContentsRepository = dayContentsRepository;
    }


    @GetMapping("/api/post/day")
    public List<DayContents> getContents() {
        return dayContentsService.getContents();
    }

    // 일정 조회
    @GetMapping("/api/post/day/{postId}")
    public Optional<DayContents> getContent(@PathVariable Long postId) {
        return dayContentsService.getContent(postId);
    }

    @GetMapping("/api/post/dayname")
    public List<DayContents> getContentname(@RequestBody DayContentsPostRequestDto dayContentsPostRequestDto) {
        return dayContentsRepository.findByNickname(dayContentsPostRequestDto.getNickname());
    }


    // 일정 생성
    @PostMapping("/api/post/day")
    public String createContents(@RequestBody DayContentsPostRequestDto dayContentsPostRequestDto) {
        return dayContentsService.createContents(dayContentsPostRequestDto);
    }

    // 일정 수정
    @PutMapping("/api/post/day/{postId}")
    public String updateCotents(@PathVariable Long postId,
                                @RequestBody DayContentsPostRequestDto dayContentsPostRequestDto) {
        return dayContentsService.updateContents(dayContentsPostRequestDto, postId);
    }

    //일정 삭제
    @DeleteMapping("/api/post/day/{postId}")
    public String deleteContents(@PathVariable Long postId, @AuthenticationPrincipal Member member) {
        return dayContentsService.deleteContents(postId);

    }

}