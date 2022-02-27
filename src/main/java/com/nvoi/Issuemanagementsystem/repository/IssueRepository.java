package com.nvoi.Issuemanagementsystem.repository;

import com.nvoi.Issuemanagementsystem.model.Issue;
import com.nvoi.Issuemanagementsystem.model.IssueState;
import com.nvoi.Issuemanagementsystem.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    //Issues by Status type
    @Query(value = "SELECT * FROM issue WHERE state='OPEN'", nativeQuery = true)
    List<Issue> getOpenIssues();

    @Query(value = "SELECT * FROM issue WHERE state='IN_PROGRESS'", nativeQuery = true)
    List<Issue> getInProgressIssues();

    @Query(value = "SELECT * FROM issue WHERE state='WAITING_ON_CLIENT'", nativeQuery = true)
    List<Issue> getWaitingOnClientIssues();

    @Query(value = "SELECT * FROM issue WHERE state='RESOLVED'", nativeQuery = true)
    List<Issue> getResolvedIssues();

    //Status table for pie chart
    @Query(value = "SELECT state as name, COUNT(state) as count FROM issue GROUP BY state", nativeQuery = true)
    List<State> getStatusOfIssues();

    List<Issue> findAllByIssueId(Long issueId);

    @Transactional
    void deleteById(Long issueId);

}
