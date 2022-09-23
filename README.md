# Search Place Service <small>SPS</small>
## 장소 검색 서비스
### 서비스 요구 사항
#### 1. 장소 검색
- 포탈 장소 검색 Open API 를 활용하여 장소 검색을 하고, 각각의 결과를 종합하여 응답 처리한다.
- 카카오 API, 네이버 API 를 통해 각각 5개씩, 종합 10개의 결과를 조회한다.
- 특정 API 가 5개 이하인 경우, 최대한 10개 맞게 결과를 더 조회한다.
- 모든 장소 검색 나열 순서는 카카오 API 기준 상위 노출한다.
  - 예시)
    - 카카오 API : A, B, C, D
    - 네이버 API : A, E, D, C
    - 결과 : A, C, D, B, E

#### 2. 검색 키워드 목록
- 많이 검색된 키워드 10개 목록 제공한다.
- 키워드별 검색된 횟수 표시한다.

---

### 프로젝트 구성
- Spring Boot
- Spring Data JPA
- H2
- MongoDB
- Caffeine Cache
- Gradle

---
