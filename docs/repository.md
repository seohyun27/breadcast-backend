## 레파지토리의 구성

### 1. Repository를 만들어야 하는 엔티티

사용자 활동이나 비즈니스 로직에서 동적으로 CRUD를 수행할 필요가 있는 엔티티

| 엔티티              |                                      |
|------------------|--------------------------------------|
| `Member`         | 사용자 계정, 로그인/삭제/정보수정 등 필요             |
| `BakeryReport`   | 사용자가 빵집 제보를 남겨야 함                    |
| `BakeryReview`   | 사용자가 리뷰 작성 가능                        |
| `FavoriteBakery` | 사용자가 즐겨찾기 추가/삭제 가능                   |
| `CoursePart`     | `Course`의 구성요소. 사용자의 입력에 따라 추가/삭제 가능 |
| `Course`         | 사용자가 코스를 만들고 삭제할 수 있음                |
| `CourseReview`   | 사용자가 리뷰 작성 가능                        |
| `FavoriteCourse` | 사용자가 즐겨찾기 추가/삭제 가능                   |
| `MenuReview`     | 사용자가 메뉴 리뷰 작성 가능                     |
| `Bakery`         | 사용자가 추가한 빵집 조회 필요                    |
| `Menu`           | 각 빵집별 메뉴 조회 필요                       


---

### 2. Repository 없이 관리 가능한 엔티티

동적으로 변하지 않고, **애플리케이션 초기 데이터(seeded data)**로만 사용되는 엔티티

| 엔티티       | 이유                                               |
| --------- |--------------------------------------------------|
| `Bread`   | 수정 없음                                |
| `Classfy` | `Menu`와 `Bread`를 매핑만 함. CRUD 필요 없음   |
| `Period`  | Enum, DB 저장용이라 Repository 필요 없음      |


> 요약 : 해당 엔티티들은 부모 엔티티를 통해 조회하거나, 서비스에서 필요할 때만 조회됨.
> Bakery, Bread, Menu, Classfy는 read-only용으로 조회만 필요하므로 서비스에서 EntityManager나 JPA query를 통해 접근

---

### 3. 쿼리문 예시
```
" select mr from MenuReview mr where mr.member = :member ORDER BY mr.date DESC "

mr.date : MenuReview 엔티티의 date 필드

DESC : 내림차순 정렬 (최신 날짜가 먼저 나오도록)
ASC : 오름차순 정렬
```


