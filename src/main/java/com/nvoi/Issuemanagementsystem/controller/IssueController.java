package com.nvoi.Issuemanagementsystem.controller;

import com.nvoi.Issuemanagementsystem.model.Issue;
import com.nvoi.Issuemanagementsystem.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @PostMapping("/add")
    public String add(@RequestBody Issue issue){
        issueService.saveIssue(issue);
        return "New Issue is added";
    }

    @GetMapping("/getAll")
    public List<Issue> getAllIssues(){
        return issueService.getAllIssues();
    }
}
