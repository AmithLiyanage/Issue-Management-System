package com.nvoi.Issuemanagementsystem.service;

import com.nvoi.Issuemanagementsystem.model.Issue;
import com.nvoi.Issuemanagementsystem.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService{

    @Autowired
    private IssueRepository issueRepository;


    @Override
    public Issue saveIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    @Override
    public List<Issue> deleteByIssueId(int issueId) {
        List<Issue> foundIssues = issueRepository.findAllByIssueId(issueId);
        if (!foundIssues.isEmpty()) {
            issueRepository.deleteById(issueId);
        }
        return foundIssues;
    }
}
