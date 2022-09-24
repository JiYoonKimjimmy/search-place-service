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

### 장소 검색 조회 Open API 정보
#### 카카오 장소 검색 API
##### 기본 정보
- DOC : [kakao developers - 로컬 API](https://developers.kakao.com/docs/latest/ko/local/dev-guide#search-by-keyword)
- Host : `dapi.kakao.com`
- URL : `GET /v2/local/search/keyword.${FORMAT}`
  - `{FORMAT}` : `JSON`(default) or `XML`

##### Parameter
| Name  |  Type   | Required | Description                         |
|:-----:|:-------:|:--------:|-------------------------------------|
| query | String  |    Y     | 검색 질의어                              |
| page  | Integer |    N     | 결과 페이지 번호 (최소: 1, 최대: 45, 기본값: 1)   |
| size  | Integer |    N     | 페이지 표시 문서 개수 (최소: 1, 최대 45, 기본값: 1) |

#### 네이버 장소 검색 API
##### 기본 정보
- DOC : [NAVER Developers - 지역 검색 API](https://developers.naver.com/docs/serviceapi/search/local/local.md#%EC%A7%80%EC%97%AD)
- Host : openapi.naver.com
- URL : `GET /v1/search/local.${FORMAT}`
  - `{FORMAT}` : `JSON`(default) or `XML`

##### Parameter
|  Name   |  Type   | Required | Description                     |
|:-------:|:-------:|:--------:|---------------------------------|
|  query  | String  |    Y     | 검색 질의어                          |
|  start  | Integer |    N     | 검색 시작 위치 (최소: 1, 기본값: 1)        |
| display | Integer |    N     | 페이지 표시 검색 결과 개수 (최소: 1, 기본값: 1) |

---

### `SPS` 장소 검색 서비스 API 상세
#### 1. 장소 검색 API
- API URL : `GET /v1/api/search/place?query=${검색 질의어}`

#### 2. 검색 키워드 목록 조회 API
- API URL : `GET /v1/api/search/place/rank`
