-- drop table if exists students;
-- create table students (id bigint not null, name varchar(50), email varchar(50), phone varchar(15), primary key (id));
SET IDENTITY_INSERT students ON;
INSERT INTO students (id, name, email, phone) VALUES (1001, 'Student 1', 'Email 1', 'Phone 1');
INSERT INTO students (id, name, email, phone) VALUES (1002, 'Student 2', 'Email 2', 'Phone 2');
INSERT INTO students (id, name, email, phone) VALUES (1003, 'Student 3', 'Email 3', 'Phone 3');
INSERT INTO students (id, name, email, phone) VALUES (1004, 'Student 4', 'Email 4', 'Phone 4');
SET IDENTITY_INSERT students OFF;