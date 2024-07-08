package com.project.second.session2.students;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;
    public StudentsService(StudentsRepository studentsRepository)
    {
        this.studentsRepository = studentsRepository;
    }

    public Optional<Students> findById(Long stuId) {
        return studentsRepository.findById(stuId);
    }

    public List<Students> findAll(Integer page, Integer size) {
        if (page < 0) {
            page = 0;
        }
        if (size > 1000) {
            size = 1000;
        }
        return studentsRepository.findAll(PageRequest.of(page, size)).getContent();
    }


    public Students create(Students student) {
        student.setStuId(System.currentTimeMillis());
        //String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return studentsRepository.save(student);
    }


    public void delete(Long id) {
        studentsRepository.deleteById(id);
    }


}
