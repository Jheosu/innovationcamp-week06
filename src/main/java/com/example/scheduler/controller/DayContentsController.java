package com.example.scheduler.controller;

import com.example.scheduler.dto.DayWeekRequestDto;
import com.example.scheduler.model.DayContents;
import com.example.scheduler.model.service.DayContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class DayContentsController {

    private final DayContentsService dayContentsService;

    @GetMapping("/api/post/day")
    public List<DayContents> getContents() {
        return dayContentsService.getContents();
    }

    @GetMapping("")
    public String test() {
        return "travis ci 자동배포 테스트변경";
    }

    // 일정 조회
    @GetMapping("/api/post/day/{postId}")
    public Optional<DayContents> getContent(@PathVariable Long postId) {
        return dayContentsService.getContent(postId);
    }


    // 일정 생성
    @PostMapping("/api/post/day")
    public String createContents(@RequestBody DayWeekRequestDto requestDto) {
        return dayContentsService.createContents(requestDto);
    }

    // 일정 수정
    @PutMapping("/api/post/day/{postId}")
    public String updateCotents(@PathVariable Long postId, @RequestBody DayWeekRequestDto requestDto) {
        return dayContentsService.updateContents(requestDto, postId);
    }

    //일정 삭제
    @DeleteMapping("/api/post/day/{postId}")
    public String deleteContents(@PathVariable Long postId) {
        return dayContentsService.deleteContents(postId);

    }

}