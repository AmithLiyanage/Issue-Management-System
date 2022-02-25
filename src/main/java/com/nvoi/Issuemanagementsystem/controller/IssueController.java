package com.nvoi.Issuemanagementsystem.controller;

import com.nvoi.Issuemanagementsystem.model.Event;
import com.nvoi.Issuemanagementsystem.model.Issue;
import com.nvoi.Issuemanagementsystem.model.IssueState;
import com.nvoi.Issuemanagementsystem.model.State;
import com.nvoi.Issuemanagementsystem.repository.IssueRepository;
import com.nvoi.Issuemanagementsystem.service.EventService;
import com.nvoi.Issuemanagementsystem.service.IssueService;
import com.nvoi.Issuemanagementsystem.service.IssueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/issue")
@CrossOrigin
public class IssueController {

    @Autowired
    private IssueService issueService;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private IssueServiceImpl issueServiceImpl;
    @Autowired
    private EventService eventService;

    //issue table
    @PostMapping("/add")
    public String add(@RequestBody Issue issue){
        issueService.saveIssue(issue);

        Event event =  new Event();
        event.setIssueId(issue.getIssueId());
        event.setToState(IssueState.OPEN.toString());
        eventService.saveEvent(event);

        return "New Issue is added & event added";
    }

    @GetMapping("/getAllIssues")
    public List<Issue> getAllIssues(){
        return issueService.getAllIssues();
    }

    @GetMapping("/getIssuesByStatus")
    public List<State> getIssuesByStatus(){
//        List<Object> st = issueRepository.getIssuesByStatus();
//        st.size();
//        System.out.println();
//        List<State> al1 = new ArrayList<>();
//        al1 = (List) st;
//        System.out.println("st.size() : "+st.size()+", List2 Value: "+al1);
//        return al1;


        return issueRepository.getIssuesByStatus();
    }

    @DeleteMapping("/deleteIssue/{issueId}")
    public ResponseEntity<List<Issue>> deleteIssueById(@PathVariable int issueId) {
        try{
            List<Issue> issue = issueService.deleteByIssueId(issueId);
            return new ResponseEntity<>(issue, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEvents/{issueId}")
    public ResponseEntity<List<Event>> get(@PathVariable Integer issueId){
        try{
            List<Event> event = eventService.getEventsByIssueId(issueId);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
