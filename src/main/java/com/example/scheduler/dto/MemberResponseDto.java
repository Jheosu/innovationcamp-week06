package com.example.scheduler.dto;

import com.example.scheduler.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String nickname;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getNickname());
    }
}
