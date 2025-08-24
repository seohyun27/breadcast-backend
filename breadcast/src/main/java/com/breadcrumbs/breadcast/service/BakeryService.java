package com.breadcrumbs.breadcast.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) // 기본을 읽기 모드로 설정 (삽입/삭제/변경 시 각 메소드에 @Transactional를 작성 -> 기본 옵션이 false)
@RequiredArgsConstructor
public class BakeryService {
}
