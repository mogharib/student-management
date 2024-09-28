package com.mogharib.student.management.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mogharib.student.management.system.entity.Student;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.Set;
@Schema(name = "course", description = "course details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    private Long id;

    @NotEmpty(message = "Course name cannot be empty")
    @Size(min = 3, max = 20, message = "Course name must be between 3 and 30 characters")
    private String courseName;

    @NotEmpty(message = "Course code cannot be empty")
    @Size(min = 3, max = 20, message = "Course code must be between 3 and 30 characters")
    @ReadOnlyProperty
    private String courseCode;

    @NotNull(message = "Course price cannot be empty")
    @Positive
    private double coursePrice;

    @NotEmpty(message = "Course description cannot be empty")
    @Size(min = 10, max = 100, message = "Course description must be between 10 and 100 characters")
    private String description;

    @NotEmpty(message = "Course instructor name cannot be empty")
    @Size(min = 3, max = 30, message = "Course instructor name must be between 3 and 30 characters")
    private String instructorName;

    @ReadOnlyProperty
    @JsonIgnore
    private Set<Student> students ;
}
