package com.project.second.session2.students;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentsController {
    private final StudentsRepository studentsRepository;
    public StudentsController(StudentsRepository studentRepository)
    {
        this.studentsRepository = studentRepository;
    }

    @GetMapping("/api/v1/students")
    public ResponseEntity<List<Students>> get (@RequestParam(name = "page", defaultValue = "0") Integer page,
                                               @RequestParam(name = "page", defaultValue = "1000") Integer size) {
        return ResponseEntity.ok(studentsRepository.findAll());
    }

    @GetMapping("/api/v1/students/{stuId}")
    public ResponseEntity<Students> get(@PathVariable("stuId") Long stuId) {
        Optional<Students> news =studentsRepository.findById(Math.toIntExact(stuId));
        if (news.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(news.get());
    }

}
