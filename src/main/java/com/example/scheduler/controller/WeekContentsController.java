package com.example.scheduler.controller;

import com.example.scheduler.dto.DayWeekRequestDto;
import com.example.scheduler.model.WeekContents;
import com.example.scheduler.model.service.WeekContentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class WeekContentsController {

    private final WeekContentsService weekContentsService;

    public WeekContentsController(WeekContentsService weekContentsService) {
        this.weekContentsService = weekContentsService;
    }


    @GetMapping("/api/post/week")
    public List<WeekContents> getContents() {
        return weekContentsService.getContents();
    }

    // 일정 조회
    @GetMapping("/api/post/week/{postId}")
    public Optional<WeekContents> getContent(@PathVariable Long postId) {
        return weekContentsService.getContent(postId);
    }


    // 일정 생성
    @PostMapping("/api/post/week")
    public String createContents(@RequestBody DayWeekRequestDto requestDto) {
        return weekContentsService.createContents(requestDto);

    }

    // 일정 수정
    @PutMapping("/api/post/week/{postId}")
    public String updateCotents(@PathVariable Long postId, @RequestBody DayWeekRequestDto requestDto) {
        return weekContentsService.updateContents(requestDto, postId);
    }

    //일정 삭제
    @DeleteMapping("/api/post/week/{postId}")
    public String deleteContents(@PathVariable Long postId) {
        return weekContentsService.deleteContents(postId);
    }

}
