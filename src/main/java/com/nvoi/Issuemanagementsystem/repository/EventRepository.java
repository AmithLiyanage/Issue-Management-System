package com.nvoi.Issuemanagementsystem.repository;

import com.nvoi.Issuemanagementsystem.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAllByIssueId(int issueId);

}
