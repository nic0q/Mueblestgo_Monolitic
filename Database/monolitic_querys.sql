show databases;

create database sueldos;
use tingeso;
show tables;
CREATE TABlE IF NOT EXISTS employee (
	id int auto_increment,
    rut char(12),
	first_name varchar(20) not null,
	last_name varchar(25) not null,
    middle_name varchar(20) null,
    category varchar(5) not null,
    PRIMARY KEY(id)
);
CREATE TABlE IF NOT EXISTS work_days (
	id int auto_increment,
    `date` DATE,
    `t_start` char(5),
    `t_exit` char(5),
    `worked_minutes` int,
    `extra_minutes` int,
    employee_id int,
    FOREIGN KEY(employee_id) REFERENCES employee(id),
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS extra_hours(
	id int auto_increment,
    `extra_minutes` int,
	employee_id int,
    FOREIGN KEY(employee_id) REFERENCES employee(id),
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS justificative(
	id int auto_increment,
    `date` DATE,
	employee_id int,
    FOREIGN KEY(employee_id) REFERENCES employee(id),
    PRIMARY KEY(id)
);
SELECT * FROM employee;
SELECT * FROM justificative;
SELECT * FROM extra_hours;
SELECT * FROM work_days;
DROP TABLE employee;
ALTER TABLE justificative ADD description varchar(500);
INSERT INTO employee(rut, `first_name`,last_name,category,middle_name) VALUES("123","nico","las","A","falso");
