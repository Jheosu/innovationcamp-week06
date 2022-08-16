package com.example.scheduler.model;

import com.example.scheduler.dto.DayContentsPostRequestDto;
import com.example.scheduler.dto.WeekContentsPostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Weekcontents")
public class WeekContents extends  Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable =false)
    private String username;

    @Column(nullable =false)
    private String contents;

    // LAZY - 삭제 동작 안해서 다시 변경
    @ManyToOne
    private Member member;

    public WeekContents(String username, String contents){
        this.username = username;
        this.contents = contents;
    }

    public void update(WeekContentsPostRequestDto requestDto){
        this.contents = requestDto.getContents();
    }

    public WeekContents(WeekContentsPostRequestDto requestDto, UserDetails userDetails){
        this.contents = requestDto.getContents();
        this.username = userDetails.getUsername();
    }
}