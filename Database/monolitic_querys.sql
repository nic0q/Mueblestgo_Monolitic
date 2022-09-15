show databases;
SHOW DATABASES;
USE tingeso;
SHOW TABLES;
CREATE TABlE IF NOT EXISTS employee (
    rut char(10),
	`name` varchar(20) not null,
	last_name varchar(25) not null,
    category varchar(5) not null,
    birth_date varchar(20),
    entry_date varchar(20),
    PRIMARY KEY(rut)
);
DROP TABLE employee;
CREATE TABlE IF NOT EXISTS worked_days (
	id int auto_increment,
    rut_employee char(10),
    `date` date,
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
DROP TABLE extra_hours;
CREATE TABLE IF NOT EXISTS justificative(
	id int auto_increment,
    `date` varchar(15),
	rut_employee char(10),
    FOREIGN KEY(rut_employee) REFERENCES employee(rut),
    PRIMARY KEY(id)
);
DROP TABLE justificative;
SELECT * FROM employee;
SELECT * FROM justificative;
SELECT * FROM extra_hours;
SELECT * FROM worked_days;

SELECT * FROM worked_days WHERE `date` > "2022-01-16";

INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("28371904-9","nico","las","A","2000/03/22","2020/03/22");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("27391503-6","WEON","SOYYO","A","2000/03/22","2015/03/22");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("21142354-k","JUANITO","KIKA","C","2000/03/22","2010/03/22");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("12457562-3","JIJAJAJA","las","A","2000/03/22","2005/03/22");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("11234123-6","ROBERTO","CARLOS","B","1999/03/22","2000/03/22");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("17765876-2","LOCO","RANDOM","B","1955/03/22","1999/03/22");
DELETE FROM employee WHERE id > 0;
DELETE FROM worked_days WHERE id > 0;
DELETE FROM extra_hours WHERE id > 0;

