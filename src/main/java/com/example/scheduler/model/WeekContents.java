package com.example.scheduler.model;

import com.example.scheduler.dto.WeekContentsPostRequestDto;
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
@Table(name = "Weekcontents")
public class WeekContents extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int daynum;

    @Column(nullable = false)
    private String title;


    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public WeekContents(String nickname, String contents) {
        this.nickname = nickname;
        this.contents = contents;
    }

    public void update(WeekContentsPostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void confirmPost(Member member) {
        this.member = member;
        member.addWeeklist(this);
    }

    public WeekContents(WeekContentsPostRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.nickname = requestDto.getNickname();
        this.daynum = Integer.parseInt(requestDto.getDaynum());
    }
}