DROP TABLE IF EXISTS organization;

CREATE TABLE organization (
  id bigint auto_increment PRIMARY KEY,
  name VARCHAR(200) NOT NULL
);

INSERT INTO organization (name) VALUES  ('ABC 회사');