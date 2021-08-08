DROP TABLE IF EXISTS organization;
DROP TABLE IF EXISTS division;
DROP TABLE IF EXISTS department1;
DROP TABLE IF EXISTS department2;

CREATE TABLE organization (
  id BIGINT auto_increment PRIMARY KEY,
  name VARCHAR(200) NOT NULL
);

INSERT INTO organization (name) VALUES  ('ABC 회사');

CREATE TABLE division (
  id BIGINT auto_increment PRIMARY KEY,
  organization_id BIGINT NOT NULL,
  name VARCHAR(200) NOT NULL,
  code VARCHAR(200) NOT NULL
);

INSERT INTO division (organization_id, name, code) VALUES  (1, '경영지원본부', 'D100');
INSERT INTO division (organization_id, name, code) VALUES  (1, 'SW 개발본부', 'D200');

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