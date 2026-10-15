package com.example.smart_student_assistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smart_student_assistant.entity.Timetable;
import com.example.smart_student_assistant.repository.TimetableRepository;

@Service
public class TimetableService {

    private final TimetableRepository timetableRepository;

    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public Timetable addTimetable(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }

    public Timetable getTimetableById(Long id) {
        return timetableRepository.findById(id).orElse(null);
    }

    public void deleteTimetable(Long id) {
        timetableRepository.deleteById(id);
    }
}