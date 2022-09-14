show databases;
SHOW DATABASES;
USE tingeso;
SHOW TABLES;
CREATE TABlE IF NOT EXISTS employee (
    rut char(10),
	`name` varchar(20) not null,
	last_name varchar(25) not null,
    category varchar(5) not null,
    service_years int,
    PRIMARY KEY(rut)
);
DROP TABLE employee;
CREATE TABlE IF NOT EXISTS worked_days (
	id int auto_increment,
    rut_employee char(10),
    `date` DATE,
    `t_entry` char(5),
    `t_exit` char(5),
    `worked_hours` int,
    `extra_hours` int,
    `late_minutes` int,
    FOREIGN KEY(rut_employee) REFERENCES employee(rut),
    PRIMARY KEY(id)
);
DROP TABLE worked_days;
CREATE TABLE IF NOT EXISTS extra_hours(
	id int auto_increment,
    `extra_hours` int,
	rut_employee char(10),
    FOREIGN KEY(rut_employee) REFERENCES employee(rut),
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS justificative(
	id int auto_increment,
    `description` VARCHAR(200),
    `date` DATE,
	rut_employee char(10),
    FOREIGN KEY(rut_employee) REFERENCES employee(rut),
    PRIMARY KEY(id)
);
DROP TABLE justificative;
DROP TABLE worked_days;
DROP TABLE extra_hours;
SELECT * FROM employee;
SELECT * FROM justificative;
SELECT * FROM extra_hours;
SELECT * FROM worked_days;
ALTER TABLE justificative ADD description varchar(500);
ALTER TABLE employee ADD service_years int;
ALTER TABLE employee RENAME COLUMN `first_name` TO `name`;
ALTER TABLE worked_days modify COLUMN `date` varchar(10);
INSERT INTO employee(rut, `name`,last_name,category,service_years) VALUES("28371904-9","nico","las","A",1);
INSERT INTO employee(rut, `name`,last_name,category,service_years) VALUES("27391503-6","WEON","SOYYO","A",5);
INSERT INTO employee(rut, `name`,last_name,category,service_years) VALUES("21142354-k","JUANITO","KIKA","C",10);
INSERT INTO employee(rut, `name`,last_name,category,service_years) VALUES("12457562-3","JIJAJAJA","las","A",13);
INSERT INTO employee(rut, `name`,last_name,category,service_years) VALUES("11234123-6","ROBERTO","CARLOS","B",20);
INSERT INTO employee(rut, `name`,last_name,category,service_years) VALUES("17765876-2","LOCO","RANDOM","B",18);
DELETE FROM employee WHERE id > 0;
DELETE FROM extra_hours WHERE id > 0;

