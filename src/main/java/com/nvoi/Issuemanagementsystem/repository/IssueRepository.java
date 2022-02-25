package com.nvoi.Issuemanagementsystem.repository;

import com.nvoi.Issuemanagementsystem.model.Event;
import com.nvoi.Issuemanagementsystem.model.Issue;
import com.nvoi.Issuemanagementsystem.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

    //@Query("SELECT state, COUNT(state) FROM issue GROUP BY state ORDER BY state")
    @Query(value = "SELECT state, COUNT(state) FROM issue GROUP BY state", nativeQuery = true)
    List<State> getIssuesByStatus();

    List<Issue> findAllByIssueId(int issueId);
}
