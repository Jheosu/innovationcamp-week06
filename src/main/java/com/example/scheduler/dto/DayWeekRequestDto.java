package com.example.scheduler.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayWeekRequestDto {
    private String title;
    private String contents;
    private String nickname;
    private int daynum;

    @Builder
    public DayWeekRequestDto(String title, String contents, String nickname, int daynum) {
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
        this.daynum = daynum;
    }
}
