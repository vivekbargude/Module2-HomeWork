package com.example.demo.dto;

import com.example.demo.annotation.UniqueDepartmentCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Department name can't be blank.")
    @Size(min = 5, max = 20, message = "Department name's character should be in range [5,20].")
    private String deptName;

    @NotBlank(message = "Manager email can't be blank.")
    @Email(message = "Manager email should be a valid email.")
    private String managerEmail;

    @NotBlank(message = "Department code can't be blank.")
    @Pattern(regexp = "^[A-Z]{3}-\\d{3}$", message = "Department code should be in format : XXX-YYY")
    @UniqueDepartmentCode
    private String deptCode;

    @AssertTrue(message = "Department should be active.")
    @JsonProperty("isActive")
    private Boolean isActive;

    @Min(value = 10, message = "Department should contain atleast 10 employees.")
    @Max(value = 100, message = "Department shouldn't contain more than 100 employees.")
    private Integer noOfEmployees;

    @PastOrPresent(message = "Date of Creation can't be a future date.")
    private LocalDateTime createdAt;
}

