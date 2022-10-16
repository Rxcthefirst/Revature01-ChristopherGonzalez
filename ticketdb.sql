CREATE SEQUENCE ticket_seq start with 10000;

CREATE TABLE IF NOT EXISTS ticketdb (
  ticket_id bigint NOT NULL DEFAULT NEXTVAL('ticket_seq'),
  employee_id bigint NOT NULL REFERENCES userdb(user_id),
  amount NUMERIC NOT NULL,
  description VARCHAR(255) NOT NULL,
  ticket_status VARCHAR(50) NOT NULL DEFAULT 'Pending',
  PRIMARY KEY (ticket_id),
  CONSTRAINT fk_user
  FOREIGN KEY (employee_id)
  REFERENCES userdb (user_id)
);