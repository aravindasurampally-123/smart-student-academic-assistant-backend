
package com.example.smart_student_assistant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.smart_student_assistant.entity.Student;
import com.example.smart_student_assistant.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(
            StudentRepository studentRepository,
            PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Create student with a BCrypt-hashed password
    public Student createStudent(Student student) {
        if (student.getPassword() == null
                || student.getPassword().isBlank()) {
            throw new IllegalArgumentException(
                    "Password is required");
        }

        student.setPassword(
                passwordEncoder.encode(student.getPassword()));

        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Update student details
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setPhone(studentDetails.getPhone());
        student.setDepartment(studentDetails.getDepartment());
        student.setYear(studentDetails.getYear());

        // Change password only when a new password is provided
        if (studentDetails.getPassword() != null
                && !studentDetails.getPassword().isBlank()) {
            student.setPassword(
                    passwordEncoder.encode(
                            studentDetails.getPassword()));
        }

        return studentRepository.save(student);
    }

    // Delete student
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // Login with automatic migration of legacy plain-text passwords
    public Student login(String email, String password) {
        Student student = studentRepository.findByEmail(email);

        if (student == null
                || password == null
                || student.getPassword() == null) {
            return null;
        }

        String storedPassword = student.getPassword();

        // Existing BCrypt-hashed password
        if (storedPassword.startsWith("$2a$")
                || storedPassword.startsWith("$2b$")
                || storedPassword.startsWith("$2y$")) {

            return passwordEncoder.matches(password, storedPassword)
                    ? student
                    : null;
        }

        // One-time migration for an existing plain-text password
        if (storedPassword.equals(password)) {
            student.setPassword(
                    passwordEncoder.encode(password));

            studentRepository.save(student);
            return student;
        }

        return null;
    }
}
