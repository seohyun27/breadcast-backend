# 🍞 breadcast - Server (빵지순례 지도 서비스)
![main image](/docs/images/breadcast-main.png)
> **"쉽게 하나로 모여있는 빵 맛집 정보, 빵지순례"** <br/>
> 빵집 리뷰 공유 및 나만의 빵지순례 루트를 기록하는 서비스입니다.

## 📅 프로젝트 개요
* **진행 기간:** 2025.09 ~ 2025.12 (약 3개월)
* **인원:** 백엔드 3명, 프론트엔드 3명 (총 6명)

## 🔗 Project Repositories
* **Documents Repository:** [Go to Documents](https://github.com/seohyun27/breadcast-docs)
* **Client Repository:** [Go to Frontend](https://github.com/nonze23/breadcast-frontend)

## 🚀 Demo
> 현재 AWS 비용 문제로 배포 서버는 중단되었습니다. 아래 시연 영상으로 주요 기능을 확인하실 수 있습니다. <br/>
> **📺 [시연 영상 링크](https://drive.google.com/file/d/1zLUFRnXhpUwhN_LXdtVT9UZSIo8RsGxM/view?usp=sharing)**

## 🛠️ Tech Stack & Decision
사용 기술과 **해당 기술을 선정한 이유**입니다.
| Category | Stack | Version | 선정 이유 |
| :--- | :--- | :--- | :--- |
| **Language** | Java | 21 | 최신 LTS 버전의 안정성과 신기능 학습 목적 |
| **Framework** | Spring Boot | 3.5.4 | 빠르고 안정적인 REST API 서버 구축 |
| **Database** | MySQL | 8.0 | 지리 정보(좌표) 저장 및 트랜잭션 관리에 용이 |
| **Infra** | AWS EC2 / S3 | - | 이미지 파일의 효율적 관리 및 확장성 고려 |
| **CI/CD** | GitHub Actions | - | 반복적인 배포 작업 자동화 |

![Java](https://img.shields.io/badge/Java-21-007396?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?logo=springsecurity&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white)
![AWS S3](https://img.shields.io/badge/AWS%20S3-569A31?logo=amazonaws&logoColor=white)

## 🏛️ System Architecture
![architecture image](/docs/images/depolyment-architecture.png)

## 💾 Domain Diagram
![domain diagram image](/docs/images/domain.png)

## 💾 ERD
![ERD image](/docs/images/erd.png)

## ⚡ Key Features (핵심 기능)
![key features image](/docs/images/key-features.png)

## 👨‍💻 Contributors & Roles (팀원 및 역할)
**Backend Team**

|          이름          |     역할     | 담당 업무                                                                                                 | GitHub |
|:--------------------:|:----------:|:------------------------------------------------------------------------------------------------------| :---: |
|       **김서현**        | **Leader** | • **전체 프로젝트 일정 관리 및 회의 주재**<br>• **DB 스키마 설계 및 JPA 엔티티 매핑 총괄**<br>• **API 응답 및 에러 처리 규격화** | [GitHub](https://github.com/seohyun27) |
|        **김서연**       |  **Core Dev**  | • **Swagger UI 연동**<br>• **사용자 인증/인가 구현** (Spring Security)<br>• **GitHub Actions 기반 CI/CD 파이프라인 구축** | [GitHub](https://github.com/seoyeoki) |
|       **박세은**        |  **Core Dev**  | • **주요 도메인의 핵심 로직 구현**<br>• **서비스 계층 단위 테스트 코드 작성**<br>• **API 연동 테스트 및 버그 픽스** | [GitHub](https://github.com/Uzrt) |

**Frontend Team**

| 이름 |       역할        | 담당 업무                |                 GitHub                  |
| :---: |:---------------:|:---------------------|:---------------------------------------:|
| **김현지** |  **Frontend**   | • **FE 환경 구축 및 기능 구현**   | [GitHub](https://github.com/rlaguswl04) |
| **노은재** |  **Frontend**   | • **API 클라이언트 구축 및 배포**  |  [GitHub](https://github.com/nonze23)   |
| **이지원** |  **Frontend**   | • **백엔드 API 연결 및 기능 구현** |  [GitHub](https://github.com/dlwleasy)  |

## 🙋‍♀️ My Contribution
- **프로젝트 총괄 및 백엔드 리딩**
  - 전체 일정 관리 및 회의 주관
  - 서비스 전체 흐름(요구사항 → API → DB) 설계

- **DB & 도메인 설계**
  - 핵심 도메인 DB 스키마 설계
  - JPA 엔티티 매핑 기준 정의 및 연관관계 설계

- **API 구조 설계 및 공통화**
  - API 응답 구조 표준화 (ApiResponse)
  - 전역 예외 처리(Global Exception Handler) 구현
  - 에러 코드 체계 설계로 클라이언트 예외 처리 단순화

- **아키텍처 및 협업 환경 구축**
  - 패키지 구조 설계 및 Base Controller/Entity 템플릿화
  - GitHub 레포지토리 및 협업 규칙 관리
  - Gradle 의존성 및 공통 설정 관리

## 🚀 Trouble Shooting & Engineering Decisions

### 1. API 응답 및 에러 처리 표준화 (클린 아키텍처 고민)
* **문제:** API마다 응답 포맷(Data, 상태 코드, 에러 메시지)이 파편화되어 프론트엔드 연동 생산성이 저하됨.
* **해결:**
    * **공통 응답 객체(`ApiResponse<T>`) 도입:** 모든 API가 성공/실패 여부, 커스텀 상태 코드, 메시지, 데이터를 동일한 포맷으로 반환하도록 규격화.
    * **전역 예외 처리(`@RestControllerAdvice`):** 비즈니스 로직에서 발생하는 커스텀 예외(`GeneralException`)를 중앙에서 캐치하여, 일관된 형태의 JSON 에러 응답으로 변환.
    * **결과:** 프론트엔드의 데이터 파싱 로직이 하나로 통합되었고, 백엔드 역시 비즈니스 로직 내에서 예외 처리 코드를 분리하여 코드 가독성(Clean Code) 대폭 향상.

<details>
<summary>📸 해결 결과 캡처 (접기/펼치기)</summary>

![trouble shooting image](/docs/images/trouble-shooting-1-1.png)
![trouble shooting image](/docs/images/trouble-shooting-1-2.png)

</details>

### 2. 세션 기반 인증 환경에서의 CORS 및 쿠키 공유 이슈 해결
* **문제:** 클라이언트(React, Localhost:3000)와 서버(Spring Boot, AWS EC2)의 Origin 분리로 인해 브라우저 SOP 위반 발생. 특히 세션 식별자인 `JSESSIONID` 쿠키가 공유되지 않아 로그인 상태 유지가 불가능함.
* **해결:**
    * `WebMvcConfigurer`를 구현하여 프론트엔드 Origin에 대해 CORS 정책을 명시적으로 개방.
    * 자격 증명(Credential) 모드를 활성화(`allowCredentials(true)`)하고, 응답 헤더에 `Set-Cookie`를 노출시켜 브라우저 간 세션 기반 인증이 정상 동작하도록 네트워크 보안 설정 최적화.

<details>
<summary>📸 해결 코드 캡처 (접기/펼치기)</summary>

![trouble shooting image](/docs/images/trouble-shooting-2.png)

</details>

### 3. JPQL JOIN 튜닝을 통한 N+1 문제 해결 및 집계 최적화
* **문제:** 빵집 목록 화면 조회 시, 각 빵집과 연관된 '리뷰 수'와 '스크랩 수'를 엔티티 그래프 탐색으로 가져오면서 빵집 개수(N)만큼의 추가 쿼리가 발생하는 **N+1 문제** 직면.
* **해결:**
    * 다건 조회 로직을 지연 로딩(Lazy Loading)에 의존하지 않고, **JPQL의 `JOIN` 문을 활용해 1회의 쿼리로 연관 데이터를 페치(Fetch)** 하도록 튜닝.
    * 애플리케이션 메모리에서 `.size()`로 개수를 세던 방식을, 쿼리 레벨에서 `COUNT(DISTINCT id)`를 수행하여 **DB 레벨 집계 연산**으로 역할을 위임.
    * **결과:** 불필요한 DB I/O를 제거하여 메인 조회 API의 응답 속도 및 메모리 사용량 대폭 개선.

<details>
<summary>📸 해결 코드 캡처 (접기/펼치기)</summary>

![trouble shooting image](/docs/images/trouble-shooting-3-1.png)
![trouble shooting image](/docs/images/trouble-shooting-3-2.png)
![trouble shooting image](/docs/images/trouble-shooting-3-3.png)

</details>

## 📂 Repository Structure
```
BreadCast-Backend/
├── .github/                   # 깃허브 액션 및 PR 템플릿
├── breadcast/                 # 스프링 부트 어플리케이션 메인 소스코드
│   ├── src/
│   └── build.gradle
├── docs/                      # 백엔드 개발을 위한 문서들
│   ├── architecture/          # 시스템 아키텍처 및 폴더 구조 설명
│   ├── guides/                # 개발 가이드라인
│   └── images/                # 리드미 및 문서용 이미지 리소스
├── database/                  # 데이터베이스 리소스
│   └── seed-images/           # 빵집 이미지
└── README.md
```

## 🚀 How to Run
### 1. Clone the repository

```Bash
git clone https://github.com/seohyun27/breadcast-backend.git
```

### 2. Configure Environment Variables
 
To run the application, you need to configure the following environment variables.

| Variable | Description | Example |
| :--- | :--- | :--- |
| `DB_HOST` | Database Host Address | `localhost` |
| `DB_PORT` | Database Port | `3306` |
| `DB_NAME` | Database Schema Name | `breadcast` |
| `DB_USERNAME` | Database Username | `root` |
| `DB_PASSWORD` | Database Password | `your_password` |

### 3. Build & Run

```Bash
cd breadcast
./gradlew bootRun
```
