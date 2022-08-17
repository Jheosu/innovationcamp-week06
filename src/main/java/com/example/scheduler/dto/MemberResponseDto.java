package com.example.scheduler.dto;


import com.example.scheduler.model.DayContents;
import com.example.scheduler.model.Member;
import com.example.scheduler.model.WeekContents;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private String nickname;

    private Long id;

    private List<DayContents> daylist;

    private List<WeekContents> weeklist;


    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(
                member.getNickname(),
                member.getId(),
                member.getDaylist(),
                member.getWeeklist()
        );
    }
}
