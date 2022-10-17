CREATE SEQUENCE ticketseq start with 10000;

CREATE TABLE IF NOT EXISTS ticketdb (
  ticket_id bigint NOT NULL DEFAULT NEXTVAL('ticketseq'),
  user_id bigint NOT NULL REFERENCES userdb(user_id),
  amount NUMERIC NOT NULL,
  description VARCHAR(255) NOT NULL,
  ticket_status VARCHAR(50) NOT NULL DEFAULT 'Pending',
  PRIMARY KEY (ticket_id),
  CONSTRAINT fk_user
  FOREIGN KEY (user_id)
  REFERENCES userdb (user_id)
);

INSERT INTO ticketdb (user_id, amount, description) 
VALUES
(10001, 100, 'Lunch'),
(10002, 123.99, 'Bus fare - Omnibus'),
(10010, 999.99, 'Travel'),
(10008, 321.21, 'Friendship tax'),
(10004, 100.00, 'Bonus'),
(10004, 200.21, 'Please don''t ask'),
(10004, 10.00, 'Lunch');