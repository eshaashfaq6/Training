package com.project.second.session2.students;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Getter
@Setter

@Entity
public class Students {
    @Id
    private Long stuId;
    private String stuName;
    private Long marks;
    private Long age;
}
