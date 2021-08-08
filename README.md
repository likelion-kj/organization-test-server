# organization-test-server
## 개발 프레임워크
* Spring Boot
* Spring JPA
* JUnit

## 테이블 설계
* 테이블명: department

|컬럼명|유형(type)|비고|
|------|---|---|
|id |BIGINT |auto_increment, primary key|
|depth|TINYINT | - |
|parent_id|BIGINT | - |
|parent_ids|VARCHAR(1000) | comma separated values |
|type|Varchar(100)|-|
|name|Varchar(200)|-|
|code|Varchar(200)|unique value|

* 테이블명: member

|컬럼명|유형(type)|비고|
|------|---|---|
|id |bigint |auto_increment, primary key|
|name|Varchar(200)|-|

* 테이블명: member_department

|컬럼명|유형(type)|비고|
|------|---|---|
|seq | BIGINT | auto_increment, primary key|
|member_id |BIGINT |-|
|department_id|BIGINT|-|
|manager_yn|char(1)|-|

## 문제해결 전략
* 데이터 저장을 위해 h2활용, 필요시 다른 데이터저장소를 활용하여 영구 저장 가능 (https://www.baeldung.com/spring-boot-h2-database)
* 에러 케이스별 핸들링(https://www.baeldung.com/exception-handling-for-rest-with-spring)

## 빌드 및 실행방법