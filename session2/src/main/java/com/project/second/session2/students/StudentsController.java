package com.project.second.session2.students;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.naming.Name;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentsController {
    private final StudentsService studentsService;
    public StudentsController(StudentsService studentsService)
    {
        this.studentsService = studentsService;
    }

    @GetMapping("/api/v1/students")
    public ResponseEntity<List<Students>> get (@RequestParam(name = "page", defaultValue = "0") Integer page,
                                               @RequestParam(name = "size", defaultValue = "1000") Integer size) {
        return ResponseEntity.ok(studentsService.findAll(page,size));
    }
    @Cacheable(cacheNames = "students",key = "#stuId")
    @GetMapping("/api/v1/students/{stuId}")
    public ResponseEntity<Students> get(@PathVariable("stuId") Long stuId) {
        Optional<Students> news =studentsService.findById(stuId);
        if (news.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(news.get());
    }


    @PreAuthorize("hasAnyAuthority('reporter')")
    @PostMapping("/api/v1/students")
    public ResponseEntity<Students> update(@RequestBody Students stu) {
         stu= studentsService.update(stu);
        return ResponseEntity.created(URI.create("/api/v1/students/" + stu.getStuId())).body(stu);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        studentsService.delete(id);
    }

}
