package com.example.smart_student_assistant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_student_assistant.entity.Student;
import com.example.smart_student_assistant.service.JwtService;
import com.example.smart_student_assistant.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    private final StudentService studentService;
    private final JwtService jwtService;

    public StudentController(
            StudentService studentService,
            JwtService jwtService) {

        this.studentService = studentService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {

        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, student)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(
                "Student deleted successfully"
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Student student) {

        Student loggedInStudent =
                studentService.login(
                        student.getEmail(),
                        student.getPassword()
                );

        if (loggedInStudent == null) {
            return ResponseEntity.status(401)
                    .body("Invalid email or password");
        }

        String token =
                jwtService.generateToken(
                        loggedInStudent.getEmail()
                );

        Map<String, Object> response = new HashMap<>();

        response.put("token", token);
        response.put("studentId", loggedInStudent.getId());
        response.put("name", loggedInStudent.getName());
        response.put("email", loggedInStudent.getEmail());

        return ResponseEntity.ok(response);
    }
}