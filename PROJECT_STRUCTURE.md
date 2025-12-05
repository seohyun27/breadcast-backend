# BreadCast Backend 프로젝트 구조

```
com.breadcrumbs.breadcast/
│
├── BreadCastApplication.java
│
├── global/
│   └── config/
│       └── SecurityConfig.java
│
└── domain/
    │
    ├── member/                          # 회원 도메인
    │   ├── controller/
    │   │   ├── AuthController.java      # 회원가입/로그인
    │   │   └── MemberController.java    # 회원 정보 관리
    │   ├── service/
    │   │   └── MemberService.java
    │   ├── repository/
    │   │   └── MemberRepository.java
    │   ├── dto/
    │   │   ├── LoginRequest.java
    │   │   ├── SignupRequest.java
    │   │   ├── MemberResponse.java
    │   │   ├── MemberUpdateRequest.java
    │   │   ├── GetMyBakeryReviewResponse.java
    │   │   ├── GetMyCourseResponse.java
    │   │   ├── GetMyCourseReviewResponse.java
    │   │   ├── GetMyMenuReviewResponse.java
    │   │   ├── GetFavoriteBakeriesResponse.java
    │   │   └── GetFavoriteCoursesResponse.java
    │   ├── entity/
    │   │   └── Member.java
    │   └── security/
    │       ├── AuthService.java
    │       └── UserDetailsImpl.java
    │
    ├── bakery/                           # 빵집 도메인
    │   ├── controller/
    │   │   └── BakeryController.java
    │   ├── service/
    │   │   └── BakeryService.java
    │   ├── repository/
    │   │   └── BakeryRepository.java
    │   ├── dto/
    │   │   ├── BakeryDetailResponse.java
    │   │   ├── SearchBakeryRequest.java
    │   │   └── SearchBakeryResponse.java
    │   └── entity/
    │       ├── Bakery.java
    │       ├── BakeryReport.java
    │       └── FavoriteBakery.java
    │
    ├── course/                           # 코스 도메인
    │   ├── controller/
    │   │   └── CourseController.java
    │   ├── service/
    │   │   ├── CourseService.java
    │   │   └── CoursePartService.java
    │   ├── repository/
    │   │   ├── CourseRepository.java
    │   │   ├── CoursePartRepository.java
    │   │   └── CourseReviewRepository.java
    │   ├── dto/
    │   │   ├── CourseRequest.java
    │   │   ├── CourseResponse.java
    │   │   ├── CourseDetailResponse.java
    │   │   ├── CoursePartRequest.java
    │   │   ├── CoursePartResponse.java
    │   │   ├── CourseReviewRequest.java
    │   │   ├── CourseReviewResponse.java
    │   │   ├── GetSimpleCoursesResponse.java
    │   │   └── SearchCourseRequest.java
    │   └── entity/
    │       ├── Course.java
    │       ├── CoursePart.java
    │       ├── CourseReview.java
    │       ├── FavoriteCourse.java
    │       └── Period.java
    │
    ├── menu/                              # 메뉴 도메인
    │   ├── controller/
    │   │   └── MenuController.java
    │   ├── service/
    │   │   └── MenuService.java
    │   ├── repository/
    │   │   ├── MenuRepository.java
    │   │   └── MenuReviewRepository.java
    │   ├── dto/
    │   │   ├── GetMenusResponse.java
    │   │   ├── GetMenuDetailResponse.java
    │   │   ├── AddMenuReviewRequest.java
    │   │   ├── UpdateMenuReviewRequest.java
    │   │   └── MenuReviewResponse.java
    │   └── entity/
    │       ├── Menu.java
    │       ├── MenuReview.java
    │       ├── Bread.java
    │       └── Classfy.java
    │
    ├── review/                            # 리뷰 도메인
    │   ├── controller/
    │   │   ├── BakeryReviewController.java
    │   │   ├── CourseReviewController.java
    │   │   └── MenuReviewController.java
    │   ├── service/
    │   │   └── ReviewService.java
    │   ├── repository/
    │   │   └── BakeryReviewRepository.java
    │   ├── dto/
    │   │   ├── BakeryReviewRequest.java
    │   │   └── BakeryReviewResponse.java
    │   └── entity/
    │       └── BakeryReview.java
    │
    ├── favorite/                          # 즐겨찾기 도메인
    │   ├── controller/
    │   │   └── FavoriteController.java
    │   ├── service/
    │   │   └── FavoriteService.java
    │   └── repository/
    │       ├── FavoriteBakeryRepository.java
    │       └── FavoriteCourseRepository.java
    │
    └── report/                             # 신고 도메인
        ├── controller/
        │   └── ReportController.java
        ├── service/
        │   └── ReportService.java
        ├── repository/
        │   └── BakeryReportRepository.java
        └── dto/
            ├── AddReportRequest.java
            └── ReportsResponse.java
```

## 도메인별 구조 설명

각 도메인은 다음과 같은 구조를 가집니다:
- **controller/**: REST API 엔드포인트
- **service/**: 비즈니스 로직
- **repository/**: 데이터 접근 계층
- **dto/**: 데이터 전송 객체
- **entity/**: JPA 엔티티

## 특수 구조

- **member/security/**: Spring Security 관련 클래스 (AuthService, UserDetailsImpl)
- **global/config/**: 전역 설정 (SecurityConfig)

