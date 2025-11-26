package com.breadcrumbs.breadcast.domain.member.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLoginIdValidator.class)
public @interface UniqueLoginId {
    String message() default "이미 사용중인 아이디가 있습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

