package com.nvoi.Issuemanagementsystem.repository;

import com.nvoi.Issuemanagementsystem.model.Issue;
import com.nvoi.Issuemanagementsystem.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query(value = "SELECT state as name, COUNT(state) as count FROM issue GROUP BY state", nativeQuery = true)
    List<State> getIssuesByStatus();

    List<Issue> findAllByIssueId(Long issueId);

    @Transactional
    void deleteById(Long issueId);


}
