## 목차
- [1. 개발환경](#개발환경)
- [2. 설계](#설계)
- [3. 핵심문제해결 전략 및 분석](#핵심문제해결-전략-및-분석)


## 개발환경
---
분야| stack |
--|--|
 |언어 | Java8|
 |프레임워크 | springBoot 2.7.0
 |DB | h2-2.1.212(tobe - oracle)
 |Data store | redis
 |빌드 툴 | maven
 |Persistence 프레임워크 | JPA/Hibernate |
 |API 테스트 툴 | Postman |
 |ERD 작성 툴 | StarUML |
 | IDE |  IntelliJ |

## 설계
### 어플리케이션 설계
- 동작 영역이 명확히 나눠짐에 따라 개발적인 측면에서 유지보수성이 높은 Spring MVC 패턴에 입각하여 개발했다.
- 다음과 같이 패키지를 나누어 구성했다.
---
Location| Comment |
--|--|
com.project.gil.api | API 요청을 처리하는 컨트롤러|
com.project.gil.api.dto | API 입출력 I/0
com.project.gil.config | 설정파일(Redis)
com.project.gil.constant | 애플리케이션내 상수
com.project.gil.service  | 비지니스 로직수행 |
com.project.gil.repository  | DB 처리 |
com.project.gil.scheduler  | 스케쥴러 |
com.project.gil.domain | JPA ENTITY 정의
com.project.gil.util | 유틸 클래스(카카오 연동로그인/ 메시지, 결제모듈) |
com.project.gil.exception | 공통 예외클래스 정의 |

### DB 테이블 설계


 
