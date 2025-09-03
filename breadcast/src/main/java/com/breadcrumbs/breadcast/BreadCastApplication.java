package com.breadcrumbs.breadcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing		// 날짜를 자동으로 저장하는 Auditing	기능 실행
public class BreadCastApplication {

	public static void main(String[] args) { SpringApplication.run(BreadCastApplication.class, args); }

}
