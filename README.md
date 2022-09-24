# 목차
- [1. 개발환경](#개발환경)
- [2. 설계](#설계)
- [3. 핵심문제해결 전략 및 분석](#핵심문제해결-전략-및-분석)


# 개발환경
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

# 설계
## 어플리케이션 설계
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

## DB 테이블 설계
<img width="80%" src="https://user-images.githubusercontent.com/20380910/192103100-22b3dc54-26c4-46a2-b7aa-dbdc4616f8e2.jpg"/>

- 총 9개의 테이블로 구성
---
TABLE명 | Comment |
--|--|
user(사용자 테이블) | 사용자 정보를 저장하는 테이블|
settle(정산 테이블) | 사용자의 정산 정보를 저장하는 테이블
payment(결제 테이블) | 사용자의 결제 정보를 저장하는 테이블
ott(ott 테이블) | ott 정보를 저장하는 테이블
wait(대기 테이블)  | 대기정보를 저장하는 테이블 |
party(파티 테이블)  | ott 파티정보를 저장하는 테이블 |
match(매치 테이블)  | 매칭정보를 저장하는 테이블 |
settle_history(정산이력 테이블) | (파티장)정산 이력을 저장하는 테이블
payment_history(결제이력 테이블) | (파티원)결제 이력을 저장하는 테이블 |

## API 설계(작업 중)

---

### 1. 사용자 카카오톡 연동 로그인
  - 설명
   - 카카오톡 인증서버로부터 인가받은 코드값으로 로그인 처리
  - Request URL
  ```
  /user/login
  ```
  - HTTP Method
  ```
  POST
  ```
  - Request
  <img width="50%" src="https://user-images.githubusercontent.com/20380910/175817542-44571653-7767-482d-8188-14bfb0055fcb.PNG"/>
  
  - Request Example
  ```
  {
    "code": "4fsjfgdjk45345ghjh"
  }
  ```
  - Response
  ```
  HTTP/1.1 200 OK
  ```

### 2. 파티 추가
  - 설명
    - 파티장이 ott 공유를 위한 파티를 신규한다.
  - Request URL
  ```
  /party/create
  ```
  - HTTP Method
  ```
  POST
  ```
  - Request
  <img width="50%" src="https://user-images.githubusercontent.com/20380910/175817542-44571653-7767-482d-8188-14bfb0055fcb.PNG"/>
  
  - Request Example
  ```
  {
    "ottId"  : "1" ,
    "leaderId" : "3" ,
    "duration" : 3 ,
    "shareId" : "zzang9083" ,
    "sharePassword" : "12345" ,
  }
  ```
  - Response
  ```
  HTTP/1.1 200 OK
  ```

### 3. 파티원 등록
  - 설명
   - 파티원이 ott 공유를 위한 파티 매칭을 신청한다.
  - Request URL
  ```
  /party/join
  ```
  - HTTP Method
  ```
  POST
  ```
  - Request
  <img width="50%" src="https://user-images.githubusercontent.com/20380910/175817542-44571653-7767-482d-8188-14bfb0055fcb.PNG"/>
  
  - Request Example
  ```
  {
    "userId" : "2" ,
    "ottId"  : "1" ,
    "duration" : 6
  }
  ```
  - Response
  ```
  HTTP/1.1 200 OK
  ```

### 4. 사용자 파티 리스트 조회
  - 설명
   - 사용자가 가입한 파티 리스트를 조회
  - Request URL
  ```
  /user/join/list
  ```
  - HTTP Method
  ```
  POST
  ```
  - Request
  <img width="50%" src="https://user-images.githubusercontent.com/20380910/175817542-44571653-7767-482d-8188-14bfb0055fcb.PNG"/>
  
  - Request Example
  ```
  {
    "userId" : "2"
  }
  ```
  - Response
  ```
  {
   {
    "ottName"  : "tving" ,
    "joinStatus" : 1
   },
   {
    "ottName"  : "netflix" ,
    "joinStatus" : 1
   },
   {
    "ottName"  : "쿠팡플레이" ,
    "joinStatus" : 0
   }
  }
  ```
    
