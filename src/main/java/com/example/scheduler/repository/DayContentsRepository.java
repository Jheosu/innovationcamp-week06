package com.example.scheduler.repository;

import com.example.scheduler.model.DayContents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayContentsRepository extends JpaRepository<DayContents, Long> {
    List<DayContents> findAllByOrderByModifiedAtDesc();

    List<DayContents> findByNickname(String nickname);


}