package com.example.scheduler.controller;

import com.example.scheduler.dto.MemberRequestDto;
import com.example.scheduler.dto.MemberResponseDto;
import com.example.scheduler.dto.TokenDto;
import com.example.scheduler.dto.TokenRequestDto;
import com.example.scheduler.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @CrossOrigin("*")
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        System.out.println(memberRequestDto);
        System.out.println("hihi");
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}