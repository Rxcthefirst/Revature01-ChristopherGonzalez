CREATE SEQUENCE user_test_seq start with 10000;

CREATE TABLE IF NOT EXISTS userdbtest (
  employee_id bigint NOT NULL DEFAULT nextval('user_test_seq'),
  first_name VARCHAR(50) DEFAULT NULL,
  last_name VARCHAR(50) DEFAULT NULL,
  username VARCHAR(50) UNIQUE NOT NULL,
  user_password VARCHAR(50) NOT NULL,
  is_manager BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (employee_id)
);

CREATE SEQUENCE ticket_seq start with 10000;

CREATE TABLE IF NOT EXISTS ticketdbtest (
  ticket_id bigint NOT NULL DEFAULT NEXTVAL('ticket_seq'),
  employee_id bigint NOT NULL REFERENCES userdbtest(employee_id),
  amount NUMERIC NOT NULL,
  description VARCHAR(255) NOT NULL,
  ticket_status VARCHAR(50) NOT NULL DEFAULT 'Pending',
  PRIMARY KEY (ticket_id),
  CONSTRAINT fk_employee
  FOREIGN KEY (employee_id)
  REFERENCES userdbtest (employee_id)
);

DROP TABLE userdbtest;
DROP TABLE ticketdbtest;
DROP SEQUENCE user_seq;
DROP SEQUENCE ticket_seq;