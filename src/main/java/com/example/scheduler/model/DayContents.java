package com.example.scheduler.model;

import com.example.scheduler.dto.DayWeekRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Daycontents")
public class DayContents extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int daynum;

    // LAZY - 삭제 동작 안해서 다시 변경
    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void update(DayWeekRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void confirmPost(Member member) {
        this.member = member;
        member.addDaylist(this);
    }

    public DayContents(DayWeekRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.nickname = requestDto.getNickname();
        this.daynum = requestDto.getDaynum();
    }

}