# organization-test-server
## 개발 프레임워크
* Spring Boot
* Spring JPA
* JUnit

## 테이블 설계
* 테이블명: company

|컬럼명|유형(type)|비고|
|------|---|---|
|id |bigint |auto_increment|
|name|Varchar(200)|-|

* 테이블명: division

|컬럼명|유형(type)|비고|
|------|---|---|
|id |bigint |auto_increment|
|company_id |bigint | - |
|name|Varchar(200)|-|
|code|Varchar(200)|-|

* 테이블명: department1

|컬럼명|유형(type)|비고|
|------|---|---|
|id |bigint |auto_increment|
|division_id |bigint | - |
|name|Varchar(200)|-|
|code|Varchar(200)|-|

* 테이블명: department2

|컬럼명|유형(type)|비고|
|------|---|---|
|id |bigint |auto_increment|
|department1_id |bigint | - |
|name|Varchar(200)|-|
|code|Varchar(200)|-|

* 테이블명: member

|컬럼명|유형(type)|비고|
|------|---|---|
|id |bigint |auto_increment|
|name|Varchar(200)|-|

* 테이블명: member_organization

|컬럼명|유형(type)|비고|
|------|---|---|
|member_id |bigint |-|
|organization_type|Varchar(100)|-|
|organization_id|bigint|-|
|manager_yn|char(1)|-|

## 문제해결 전략
* 데이터 저장을 위해 h2활용, 필요시 다른 데이터저장소를 활용하여 영구 저장 가능 (https://www.baeldung.com/spring-boot-h2-database)

## 빌드 및 실행방법