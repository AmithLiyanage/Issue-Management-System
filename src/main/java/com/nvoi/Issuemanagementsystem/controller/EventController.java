package com.nvoi.Issuemanagementsystem.controller;

import com.nvoi.Issuemanagementsystem.model.Event;
import com.nvoi.Issuemanagementsystem.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequestMapping("/event")
public class EventController {

   /* @Autowired
    private EventService eventService;

    @PostMapping("/addEvent")
    public String add(@RequestBody Event event){
        eventService.saveEvent(event);
        return "New Event is recorded";
    }

    @GetMapping("/getEvents/{issueId}")
    public ResponseEntity<Event> get(@PathVariable Integer issueId){
        try{
            //Event event = eventService.get(issueId);
            //return new ResponseEntity<Event>(event, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }
    }*/

}
