package com.breadcrumbs.breadcast.domain.member.validation;

import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueNicknameValidator implements ConstraintValidator<UniqueNickname, String> {

    private final MemberRepository memberRepository;

    @Override
    public void initialize(UniqueNickname constraintAnnotation) {
    }

    @Override
    public boolean isValid(String nickname, ConstraintValidatorContext context) {
        if (nickname == null || nickname.isBlank()) {
            return true; // null이나 빈 값은 다른 validator에서 처리
        }
        
        // 중복이 없으면 true (사용 가능)
        // 중복이 있으면 false (사용 불가)
        return !memberRepository.existsByNickname(nickname);
    }
}

