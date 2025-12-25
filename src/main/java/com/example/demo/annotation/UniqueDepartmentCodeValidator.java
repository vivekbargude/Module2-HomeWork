package com.example.demo.annotation;

import com.example.demo.repository.DepartmentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueDepartmentCodeValidator
        implements ConstraintValidator<UniqueDepartmentCode, String> {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !departmentRepository.existsByDeptCode(value);
    }
}
