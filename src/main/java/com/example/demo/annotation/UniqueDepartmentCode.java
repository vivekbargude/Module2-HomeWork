package com.example.demo.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDepartmentCodeValidator.class)
@Documented
public @interface UniqueDepartmentCode {

    String message() default "Department code already exists.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
