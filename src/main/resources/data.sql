DROP TABLE IF EXISTS member_department;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS member;

CREATE TABLE department (
  id BIGINT auto_increment PRIMARY KEY,
  depth TINYINT NOT NULL,
  parent_id BIGINT,
  parent_ids VARCHAR(1000) NOT NULL,
  type VARCHAR(100) NOT NULL,
  name VARCHAR(200) NOT NULL,
  code VARCHAR(200)
);

ALTER TABLE department ADD UNIQUE KEY (code);

CREATE TABLE member (
  id BIGINT auto_increment PRIMARY KEY,
  name VARCHAR(200) NOT NULL
);

CREATE TABLE member_department (
  seq BIGINT auto_increment PRIMARY KEY,
  member_id BIGINT NOT NULL,
  department_id BIGINT NOT NULL,
  manager_yn CHAR(1) NOT NULL
);

ALTER TABLE member_department ADD UNIQUE KEY (member_id, department_id);

INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (1, null, '', 'COMPANY', 'ABC회사', null);
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (1, 1, '1', 'DIVISION', '경영지원본부', 'D100');
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (1, 1, '1', 'DIVISION', 'SW 개발본부', 'D200');
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (2, 2, '1,2', 'DEPARTMENT', '인사팀', 'D110');
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (2, 2, '1,2', 'DEPARTMENT', '총무팀', 'D120');
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (2, 2, '1,2', 'DEPARTMENT', '법무팀', 'D130');
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (2, 3, '1,3', 'DEPARTMENT',  '플랫폼개발부', 'D210');
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (2, 3, '1,3', 'DEPARTMENT',  '비즈서비스개발부', 'D220');
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (3, 7, '1,3,7', 'DEPARTMENT',  '비즈플랫폼팀', 'D211');
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (3, 7, '1,3,7', 'DEPARTMENT',  '비즈서비스팀', 'D212');
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (3, 7, '1,3,7', 'DEPARTMENT',  '그룹웨어개발팀', 'D213');
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (3, 8, '1,3,8', 'DEPARTMENT',  '플랫폼서비스팀', 'D221');
INSERT INTO department (depth, parent_id, parent_ids, type, name, code) VALUES  (3, 8, '1,3,8', 'DEPARTMENT',  '모바일개발팀', 'D222');

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

INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (1, 1, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (2, 2, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (3, 3, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (4, 4, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (5, 4, 'N');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (6, 4, 'N');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (7, 5, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (8, 5, 'N');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (9, 6, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (10, 6, 'N');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (11, 7, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (11, 9, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (12, 8, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (13, 9, 'N');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (14, 9, 'N');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (15, 10, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (16, 10, 'N');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (17, 11, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (18, 11, 'N');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (19, 12, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (20, 12, 'N');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (21, 13, 'Y');
INSERT INTO member_department (member_id, department_id, manager_yn) VALUES  (22, 13, 'N');