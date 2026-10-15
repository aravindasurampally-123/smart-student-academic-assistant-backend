package com.example.smart_student_assistant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_student_assistant.entity.Timetable;
import com.example.smart_student_assistant.repository.TimetableRepository;
import com.example.smart_student_assistant.service.TimetableService;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {

    private final TimetableService timetableService;
    private final TimetableRepository timetableRepository;

    public TimetableController(
            TimetableService timetableService,
            TimetableRepository timetableRepository) {

        this.timetableService = timetableService;
        this.timetableRepository = timetableRepository;
    }

    @PostMapping
    public Timetable addTimetable(@RequestBody Timetable timetable) {
        return timetableService.addTimetable(timetable);
    }

    @GetMapping
    public List<Timetable> getAllTimetables() {
        return timetableService.getAllTimetables();
    }

    @GetMapping("/student/{studentId}")
    public List<Timetable> getTimetableByStudent(
            @PathVariable Long studentId) {

        return timetableRepository.findByStudentId(studentId);
    }

    @GetMapping("/{id}")
    public Timetable getTimetableById(@PathVariable Long id) {
        return timetableService.getTimetableById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTimetable(@PathVariable Long id) {
        timetableService.deleteTimetable(id);
    }
}
