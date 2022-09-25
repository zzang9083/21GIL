# 목차
- [1. 개요](#개요)
- [2. 개발환경](#개발환경)
- [3. 설계](#설계)
- [4. 핵심문제해결 전략](#핵심문제해결 전략)

# 개요
21GIL은 OTT 서비스 요금 공유를 목적으로 파티를 찾고자하는 유저들을 위한 커뮤니티 사이트입니다.
 - 파티장이 계정을 공유하는 파티를 개설하면, 파티원 최대 3명이 공유받을 수 있게끔 중개합니다.
 - 자동매칭기능을 지원하여 파티원의 파티 매칭과 관리를 용이하게 해줍니다. 

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
- 서비스 자체의 비즈니스 로직이 아닌 카카오톡을 통한 로그인, 메시지 / 결제 모듈은 유틸클래스로 분류했다.
- 정기적으로 일어나는 동작은 스프링 스케쥴러를 활용하여 구현했고, 스케쥴러로 분류했다.
- 다음과 같이 패키지를 나누어 구성했다. 
---
Location| Comment |
--|--|
com.project.gil.api | API 요청을 처리하는 컨트롤러|
com.project.gil.api.dto | API 입출력 I/0
com.project.gil.config | 설정파일(Redis)
com.project.gil.constant | 애플리케이션 내 상수
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
  curl --request GET \
  --url 'https://gil.com/user/login?code=4fsjfgdjk45345ghjh'
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
  GET
  ```
  - Request
  <img width="50%" src="https://user-images.githubusercontent.com/20380910/175817542-44571653-7767-482d-8188-14bfb0055fcb.PNG"/>
  
  - Request Example
  ```
  curl --request GET \
  --url 'https://gil.com/user/join/list?userid=12354'
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
# 핵심문제해결 전략

## 자동매칭기능 구현

- 스프링 스케쥴러를 활용한 배치작업 수행
 - 5초단위로 동작하는 스케쥴러를 구성하여 파티 매칭을 수행하도록 했다.
 - 매칭 후 파티구성이 완료된(구성원 4명) 파티는 파티원들에게 파티매칭완료를 알리는 카카오톡 메시지가 간다.
- REDIS를 활용한 메시지 큐 구현
 - 파티신청 대기열(FOR 파티원)을 인메모리 파일 저장소인 REDIS로 구성했다.
   - DB ACCESS가 줄어듦으로서 성능적으로 개선된다.
   - REDIS에서 제공하는 데이터 자료구조를 활용하여 프로젝트의 기능을 구현할 수 있다.(QUEUE를 사용하여 요청에 대한 순차적인 처리)
   - 추후 서비스 서버를 증설하고자 할 때, REDIS의 데이터 동기화 기능으로 다중화된 서버에도 활용이 가능하도록 한다.
 - RedisTemplate를 활용하여 선입선출 자료구조인 QUEUE를 생성하여 파티신청 정보를 큐에 넣어, 순차적으로 처리하도록 했다.
 - 세 차례까지 리매치 시도 후에 파티 매칭이 안되면 별도 생성한 우선순위 큐에 넣어, 매칭을 진행한다.(우선순위 매칭은 일반 매칭에 항시 선행된다)
 

