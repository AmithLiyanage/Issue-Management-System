package com.nvoi.Issuemanagementsystem.repository;

import com.nvoi.Issuemanagementsystem.model.Event;
import com.nvoi.Issuemanagementsystem.model.Issue;
import com.nvoi.Issuemanagementsystem.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    //Next Issue ID for UI
    @Query(value = "SELECT * FROM issue ORDER BY issue_id DESC LIMIT 1", nativeQuery = true)
    Issue getNextIssue();

    //Status table for pie chart
    @Query(value = "SELECT state as name, COUNT(state) as count FROM issue WHERE submitted_by=? GROUP BY state", nativeQuery = true)
    List<State> getStatusOfIssues(String submittedBy);

    //Issues list
    @Query(value = "SELECT * FROM issue WHERE submitted_by=? ORDER BY issue_id DESC", nativeQuery = true)
    List<Issue> getAllIssues(String submittedBy);

    @Query(value = "SELECT * FROM issue WHERE state='OPEN' AND submitted_by=? ORDER BY issue_id DESC", nativeQuery = true)
    List<Issue> getOpenIssues(String submittedBy);

    @Query(value = "SELECT * FROM issue WHERE state='IN_PROGRESS' AND submitted_by=? ORDER BY issue_id DESC", nativeQuery = true)
    List<Issue> getInProgressIssues(String submittedBy);

    @Query(value = "SELECT * FROM issue WHERE state='WAITING_ON_CLIENT' AND submitted_by=? ORDER BY issue_id DESC", nativeQuery = true)
    List<Issue> getWaitingOnClientIssues(String submittedBy);

    @Query(value = "SELECT * FROM issue WHERE state='RESOLVED' AND submitted_by=? ORDER BY issue_id DESC", nativeQuery = true)
    List<Issue> getResolvedIssues(String submittedBy);
}
