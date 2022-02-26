package com.nvoi.Issuemanagementsystem.service;

import com.nvoi.Issuemanagementsystem.model.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    public Issue saveIssue(Issue issue);
    public List<Issue> getAllIssues();
    public Issue deleteByIssueId(long id);
}

