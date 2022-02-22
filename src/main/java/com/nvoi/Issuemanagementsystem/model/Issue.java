package com.nvoi.Issuemanagementsystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
public class Issue {

    public enum IssueType {
        BUG, QUESTION, IMPROVEMENT
    }

    public enum IssueState {
        OPEN, IN_PROGRESS, WAITING_ON_CLIENT, RESOLVED
    }

    @Id//Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment & generated
    private int issueId;
    private String issueName;
    private String type;
    private String state;
    private String description;
    private String createdTime;
    private String metaData;

    public Issue() {
        this.createdTime = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss", Locale.ENGLISH));
        this.state = IssueState.OPEN.name();
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
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

    public String getCreatedTime() {
        return createdTime;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }
}
