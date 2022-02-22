package com.nvoi.Issuemanagementsystem.service;

import com.nvoi.Issuemanagementsystem.model.Issue;

import java.util.List;

public interface IssueService {
    public Issue saveIssue(Issue issue);
    public List<Issue> getAllIssues();
}
