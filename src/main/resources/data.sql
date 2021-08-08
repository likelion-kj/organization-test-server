DROP TABLE IF EXISTS organization;
DROP TABLE IF EXISTS division;

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