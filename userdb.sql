CREATE SEQUENCE userseq start with 10000;

CREATE TABLE IF NOT EXISTS userdb (
  user_id bigint NOT NULL DEFAULT nextval('userseq'),
  first_name VARCHAR(50) DEFAULT NULL,
  last_name VARCHAR(50) DEFAULT NULL,
  username VARCHAR(50) UNIQUE NOT NULL,
  user_password VARCHAR(50) NOT NULL,
  is_manager BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (user_id)
);

INSERT INTO userdb (first_name, last_name, username, user_password, is_manager) 
VALUES
(DEFAULT, DEFAULT, 'root', 'root', TRUE),
('Oliver', 'Robinson', 'Havok22', 'fatbottom', DEFAULT),
('Christopher', 'Seymour', 'Hamjam', 'howdyneighbor', DEFAULT),
('Jet', 'Violetta', 'JettYoubetcha', 'youbet', DEFAULT),
('Genji', 'Genji', 'Genji', 'GENJI', DEFAULT),
('Christopher', 'Gonzalez', 'Rxcthefirst', 'PASSWORD', DEFAULT),
('Blake', 'Maurer', 'Droop', 'BrettSucks', DEFAULT),
('Brett', 'Maurer', 'FizzlePop', 'BlakeStinks', DEFAULT),
(DEFAULT, DEFAULT, 'USERNAME', 'PASSWORD', DEFAULT),
(DEFAULT, DEFAULT, 'Username', 'Password', DEFAULT),
(DEFAULT, DEFAULT, 'username', 'password', DEFAULT);