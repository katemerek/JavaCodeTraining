package com.github.katemerek.javacodetraining.Stream.task2;

import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Data
class Student {
    private String name;
    private Map<String, Integer> grades;

    public Student(String name, Map<String, Integer> grades) {
        this.name = name;
        this.grades = grades;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }
}
