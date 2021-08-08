DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS division;
DROP TABLE IF EXISTS department1;
DROP TABLE IF EXISTS department2;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS member_organization;

CREATE TABLE company (
  id BIGINT auto_increment PRIMARY KEY,
  name VARCHAR(200) NOT NULL
);

INSERT INTO company (name) VALUES  ('ABC 회사');

CREATE TABLE division (
  id BIGINT auto_increment PRIMARY KEY,
  company_id BIGINT NOT NULL,
  name VARCHAR(200) NOT NULL,
  code VARCHAR(200) NOT NULL
);

INSERT INTO division (company_id, name, code) VALUES  (1, '경영지원본부', 'D100');
INSERT INTO division (company_id, name, code) VALUES  (1, 'SW 개발본부', 'D200');

CREATE TABLE department1 (
  id BIGINT auto_increment PRIMARY KEY,
  division_id BIGINT NOT NULL,
  name VARCHAR(200) NOT NULL,
  code VARCHAR(200) NOT NULL
);

INSERT INTO department1 (division_id, name, code) VALUES  (1, '인사팀', 'D110');
INSERT INTO department1 (division_id, name, code) VALUES  (1, '총무팀', 'D120');
INSERT INTO department1 (division_id, name, code) VALUES  (1, '법무팀', 'D130');
INSERT INTO department1 (division_id, name, code) VALUES  (2, '플랫폼개발부', 'D210');
INSERT INTO department1 (division_id, name, code) VALUES  (2, '비즈서비스개발부', 'D220');

CREATE TABLE department2 (
  id BIGINT auto_increment PRIMARY KEY,
  department1_id BIGINT NOT NULL,
  name VARCHAR(200) NOT NULL,
  code VARCHAR(200) NOT NULL
);

INSERT INTO department2 (department1_id, name, code) VALUES  (4, '비즈플랫폼팀', 'D211');
INSERT INTO department2 (department1_id, name, code) VALUES  (4, '비즈서비스팀', 'D212');
INSERT INTO department2 (department1_id, name, code) VALUES  (4, '그룹웨어개발팀', 'D213');
INSERT INTO department2 (department1_id, name, code) VALUES  (5, '플랫폼서비스팀', 'D221');
INSERT INTO department2 (department1_id, name, code) VALUES  (5, '모바일개발팀', 'D222');

CREATE TABLE member (
  id BIGINT auto_increment PRIMARY KEY,
  name VARCHAR(200) NOT NULL
);

INSERT INTO member (name) VALUES  ('사장1');
INSERT INTO member (name) VALUES  ('경영1');
INSERT INTO member (name) VALUES  ('SW1');
INSERT INTO member (name) VALUES  ('인사1');
INSERT INTO member (name) VALUES  ('인사2');
INSERT INTO member (name) VALUES  ('인사3');
INSERT INTO member (name) VALUES  ('총무1');
INSERT INTO member (name) VALUES  ('총무2');
INSERT INTO member (name) VALUES  ('법무1');
INSERT INTO member (name) VALUES  ('법무2');
INSERT INTO member (name) VALUES  ('플랫폼1');
INSERT INTO member (name) VALUES  ('서비스1');
INSERT INTO member (name) VALUES  ('개발1');
INSERT INTO member (name) VALUES  ('개발2');
INSERT INTO member (name) VALUES  ('개발3');
INSERT INTO member (name) VALUES  ('개발4');
INSERT INTO member (name) VALUES  ('개발5');
INSERT INTO member (name) VALUES  ('개발6');
INSERT INTO member (name) VALUES  ('개발7');
INSERT INTO member (name) VALUES  ('개발8');
INSERT INTO member (name) VALUES  ('개발9');
INSERT INTO member (name) VALUES  ('개발10');

CREATE TABLE member_organization (
  member_id BIGINT NOT NULL,
  organization_type VARCHAR(100) NOT NULL,
  organization_id BIGINT NOT NULL,
  manager_yn CHAR(1) NOT NULL
);
ALTER TABLE member_organization ADD PRIMARY KEY (member_id, organization_type, organization_id);

INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (1, 'COMPANY', 1, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (2, 'DIVISION', 1, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (3, 'DIVISION', 2, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (4, 'DEPARTMENT1', 1, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (5, 'DEPARTMENT1', 1, 'N');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (6, 'DEPARTMENT1', 1, 'N');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (7, 'DEPARTMENT1', 2, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (8, 'DEPARTMENT1', 2, 'N');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (9, 'DEPARTMENT1', 3, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (10, 'DEPARTMENT1', 3, 'N');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (11, 'DEPARTMENT1', 4, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (11, 'DEPARTMENT2', 1, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (12, 'DEPARTMENT1', 5, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (13, 'DEPARTMENT2', 1, 'N');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (14, 'DEPARTMENT2', 1, 'N');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (15, 'DEPARTMENT2', 2, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (16, 'DEPARTMENT2', 2, 'N');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (17, 'DEPARTMENT2', 3, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (18, 'DEPARTMENT2', 3, 'N');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (19, 'DEPARTMENT2', 4, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (20, 'DEPARTMENT2', 4, 'N');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (21, 'DEPARTMENT2', 5, 'Y');
INSERT INTO member_organization (member_id, organization_type, organization_id, manager_yn) VALUES  (22, 'DEPARTMENT2', 5, 'N');