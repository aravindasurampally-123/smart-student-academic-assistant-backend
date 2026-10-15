package com.example.smart_student_assistant.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.smart_student_assistant.repository.AssignmentRepository;
import com.example.smart_student_assistant.repository.AttendanceRepository;
import com.example.smart_student_assistant.repository.MarksRepository;
import com.example.smart_student_assistant.repository.NotificationRepository;
import com.example.smart_student_assistant.repository.StudentRepository;
import com.example.smart_student_assistant.repository.StudyMaterialRepository;
import com.example.smart_student_assistant.repository.StudyProgressRepository;
import com.example.smart_student_assistant.repository.SubjectRepository;
import com.example.smart_student_assistant.repository.TimetableRepository;

@Service
public class DashboardService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final AttendanceRepository attendanceRepository;
    private final MarksRepository marksRepository;
    private final AssignmentRepository assignmentRepository;
    private final TimetableRepository timetableRepository;
    private final NotificationRepository notificationRepository;
    private final StudyMaterialRepository studyMaterialRepository;
    private final StudyProgressRepository studyProgressRepository;

    public DashboardService(
            StudentRepository studentRepository,
            SubjectRepository subjectRepository,
            AttendanceRepository attendanceRepository,
            MarksRepository marksRepository,
            AssignmentRepository assignmentRepository,
            TimetableRepository timetableRepository,
            NotificationRepository notificationRepository,
            StudyMaterialRepository studyMaterialRepository,
            StudyProgressRepository studyProgressRepository) {

        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.attendanceRepository = attendanceRepository;
        this.marksRepository = marksRepository;
        this.assignmentRepository = assignmentRepository;
        this.timetableRepository = timetableRepository;
        this.notificationRepository = notificationRepository;
        this.studyMaterialRepository = studyMaterialRepository;
        this.studyProgressRepository = studyProgressRepository;
    }

    public Map<String, Object> getDashboard(Long studentId) {

        Map<String, Object> dashboard = new LinkedHashMap<>();

        dashboard.put("student",
                studentRepository.findById(studentId).orElse(null));

        dashboard.put("subjects",
                subjectRepository.findAll());

        dashboard.put("attendance",
                attendanceRepository.findByStudentId(studentId));

        dashboard.put("marks",
                marksRepository.findByStudentId(studentId));

        dashboard.put("assignments",
                assignmentRepository.findAll());

        dashboard.put("timetable",
                timetableRepository.findByStudentId(studentId));

        dashboard.put("notifications",
                notificationRepository.findByStudentId(studentId));

        dashboard.put("studyMaterials",
                studyMaterialRepository.findAll());

        dashboard.put("studyProgress",
                studyProgressRepository.findByStudentId(studentId));

        return dashboard;
    }
}
