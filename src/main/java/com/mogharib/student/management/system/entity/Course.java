package com.mogharib.student.management.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "course")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // Use this field for equals and hashcode
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @Column(name = "course_price", nullable = false)
    private double coursePrice;

    @Column(name = "course_description")
    private String description;

    @Column(name = "course_instructor", nullable = false)
    private String instructorName;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.PERSIST)
    @JsonIgnore // Prevent circular reference
    private Set<Student> students = new HashSet<>();


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", students=" + (students != null ? students.size() : 0) +
                '}';
    }


}
