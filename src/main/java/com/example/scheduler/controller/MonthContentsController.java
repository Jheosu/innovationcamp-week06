package com.example.scheduler.controller;

import com.example.scheduler.dto.MonthContentsPostRequestDto;
import com.example.scheduler.model.MonthContents;
import com.example.scheduler.service.MonthContentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class MonthContentsController {

    private final MonthContentsService monthContentsService;

    public MonthContentsController(MonthContentsService monthContentsService) {
        this.monthContentsService = monthContentsService;
    }


    @GetMapping("/api/post/month")
    public List<MonthContents> getContents() {
        return monthContentsService.getContents();
    }

    // 일정 조회
    @GetMapping("/api/post/month/{postId}")
    public Optional<MonthContents> getContent(@PathVariable Long postId) {
        return monthContentsService.getContent(postId);
    }


    // 일정 생성
    @PostMapping("/api/post/month")
    public String createContents(@RequestBody MonthContentsPostRequestDto monthContentsPostRequestDto) {
        return monthContentsService.createContents(monthContentsPostRequestDto);

    }

    // 일정 수정
    @PutMapping("/api/post/month/{postId}")
    public String updateCotents(@PathVariable Long postId,
                                @RequestBody MonthContentsPostRequestDto monthContentsPostRequestDto) {
        return monthContentsService.updateContents(monthContentsPostRequestDto, postId);
    }

    //일정 삭제
    @DeleteMapping("/api/post/month/{postId}")
    public String deleteContents(@PathVariable Long postId) {
        return monthContentsService.deleteContents(postId);

    }

}