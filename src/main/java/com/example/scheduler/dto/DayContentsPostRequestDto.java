package com.example.scheduler.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayContentsPostRequestDto {
    private String title;
    private String contents;
    private String nickname;
    private int daynum;

    @Builder
    public DayContentsPostRequestDto(String title, String contents, String nickname, int daynum) {
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
        this.daynum = daynum;
    }

}