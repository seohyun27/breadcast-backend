package com.breadcrumbs.breadcast.domain.member.validation;

import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueLoginIdValidator implements ConstraintValidator<UniqueLoginId, String> {

    private final MemberRepository memberRepository;

    @Override
    public void initialize(UniqueLoginId constraintAnnotation) {
    }

    @Override
    public boolean isValid(String loginId, ConstraintValidatorContext context) {
        if (loginId == null || loginId.isBlank()) {
            return true; // null이나 빈 값은 다른 validator에서 처리
        }
        
        // 중복이 없으면 true (사용 가능)
        // 중복이 있으면 false (사용 불가)
        return !memberRepository.findByLoginId(loginId).isPresent();
    }
}

