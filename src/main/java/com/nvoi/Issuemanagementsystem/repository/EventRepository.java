package com.nvoi.Issuemanagementsystem.repository;

import com.nvoi.Issuemanagementsystem.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByIssueId(long issueId);

    @Query(value = "SELECT * FROM event WHERE issue_id = 7 ORDER BY event_time DESC limit 1;", nativeQuery = true)
    Event getLastEventBIssueId(long issueId);

}
