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