package com.example.scheduler.repository;


import com.example.scheduler.model.WeekContents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekContentsRepository extends JpaRepository<WeekContents, Long> {
    List<WeekContents> findAllByOrderByModifiedAtDesc();

    List<WeekContents> findByNickname(String nickname);

    List<WeekContents> findByDaynum(int daynum);
}