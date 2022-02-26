package com.nvoi.Issuemanagementsystem.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Issue {

    public enum IssueType {
        BUG, QUESTION, IMPROVEMENT
    }

    @Id//Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment & generated
    private long issueId;
    private String issueName;
    private String type;
    private String state;
    private String description;

    @OneToMany(targetEntity = Event.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "issueId", referencedColumnName = "issueId")
    private List<Event> events;


    public Issue() {
        this.state = IssueState.OPEN.name();
    }

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type.toString();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
