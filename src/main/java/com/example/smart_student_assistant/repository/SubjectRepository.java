package com.example.smart_student_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smart_student_assistant.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}