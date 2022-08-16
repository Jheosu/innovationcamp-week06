package com.example.scheduler.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    private List<DayContents> daylist = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    private List<WeekContents> weeklist = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    private List<MonthContents> monthlist = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Authority authority;


    @Builder
    public Member(String nickname, String username, String password, Authority authority) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public void addDaylist(DayContents dayContents) {
        daylist.add(dayContents);
    }

    public void addWeeklist(WeekContents weekContents) {
        weeklist.add(weekContents);
    }

    public void addMonthlist(MonthContents monthContents) {
        monthlist.add(monthContents);
    }
}