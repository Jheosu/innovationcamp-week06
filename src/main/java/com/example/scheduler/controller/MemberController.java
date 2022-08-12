package com.example.scheduler.controller;

import com.example.scheduler.dto.MemberResponseDto;
import com.example.scheduler.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String nickname) {
        return ResponseEntity.ok(memberService.getMemberInfo(nickname));
    }
}
