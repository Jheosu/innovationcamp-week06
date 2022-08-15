package com.example.scheduler.controller;

import com.example.scheduler.dto.WeekContentsPostRequestDto;
import com.example.scheduler.model.Member;
import com.example.scheduler.model.WeekContents;
import com.example.scheduler.service.WeekContentsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class WeekContentsController {

    private final WeekContentsService weekContentsService;

    public WeekContentsController(WeekContentsService weekContentsService){
        this.weekContentsService = weekContentsService;
    }


    @GetMapping("/api/contents/week")
    public List<WeekContents> getContents() {
        return weekContentsService.getContents();
    }

    // 일정 조회
    @GetMapping("/api/contents/week/{postId}")
    public Optional<WeekContents> getContent(@PathVariable Long postId){
        return weekContentsService.getContent(postId);
    }


    // 일정 생성
    @PostMapping("/api/contents/week")
    public String createContents(@RequestBody WeekContentsPostRequestDto weekContentsPostRequestDto,
                                 @AuthenticationPrincipal Member member) {
        return weekContentsService.createContents(weekContentsPostRequestDto, (UserDetails) member);

    }

    // 일정 수정
    @PutMapping("/api/contents/week/{postId}")
    public String updateCotents(@PathVariable Long postId,
                                @RequestBody WeekContentsPostRequestDto weekContentsPostRequestDto,
                                @AuthenticationPrincipal Member member){
        return weekContentsService.updateContents(weekContentsPostRequestDto, postId, (UserDetails) member);
    }

    //일정 삭제
    @DeleteMapping("/api/contents/week/{postId}")
    public String deleteContents(@PathVariable Long postId, @AuthenticationPrincipal Member member){
        return weekContentsService.deleteContents(postId, (UserDetails) member);

    }

}