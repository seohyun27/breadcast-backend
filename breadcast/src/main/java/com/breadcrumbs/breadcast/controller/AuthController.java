package com.breadcrumbs.breadcast.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


/**
 * 모든 컨트롤러 클래스에서
 * 각 메소드별 api 매핑 파트 포함하지 않음
 * 인자값 어노테이션 처리 없음
 * 필요하다면 @RequestMapping("/api/v1/...") 사용해 공통 경로 지정할 수 있음
 */

@RestController
@RequiredArgsConstructor
public class AuthController {


}
