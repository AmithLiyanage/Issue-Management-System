package com.nvoi.Issuemanagementsystem.repository;

import com.nvoi.Issuemanagementsystem.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
