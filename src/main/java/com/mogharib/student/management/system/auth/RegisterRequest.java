package com.mogharib.student.management.system.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "student",
        description = "Register student")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegisterRequest {
    @NotEmpty
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
    @Schema(description = "Student's First of Name", example = "mohamed")
    private String firstName;

    @NotEmpty
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    @Schema(description = "Student's First of Name", example = "gharib")
    private String lastName;

    @Min(10)
    @Max(80)
    @Schema(description = "Student's age", example = "26")
    private int age;

    @Size(min = 10, max = 100, message = "Address must be between 10 and 100 characters")
    @Schema(description = "Student's address", example = "waraq")
    private String address;

    @Email(message = "Invalid email address")
    @Schema(description = "Student's email", example = "9mohamedabdelhamied8@gmail.com")
    private String email;

    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    @Schema(description = "Student's password", example = "mohamed123")
    private String password;

    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    @Schema(description = "Student's confirm password", example = "mohamed123")
    private String confirmPassword;

    @Pattern(regexp = "^[0-9][0-9]{8,12}$", message = "Invalid mobile number")
    @Schema(description = "Student's phone number", example = "01555045713")
    private String phoneNumber;
}
