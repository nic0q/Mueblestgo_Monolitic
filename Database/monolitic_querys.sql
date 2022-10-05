SHOW DATABASES;
USE mueblestgo;
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
CREATE TABlE IF NOT EXISTS worked_days (
	id int auto_increment,
    rut_employee char(10),
    `date` date,
    `extra_hours` int,
    `late_minutes` int,
    FOREIGN KEY(rut_employee) REFERENCES employee(rut),
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS extra_hours(
	id int auto_increment,
    `date` date,
    n_hours int,
	rut_employee char(10),
    FOREIGN KEY(rut_employee) REFERENCES employee(rut),
    PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS justificative(
	id int auto_increment,
    `date` date,
	rut_employee char(10),
    FOREIGN KEY(rut_employee) REFERENCES employee(rut),
    PRIMARY KEY(id)
);
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("20457671-9","Rhaenyra","Targaryen","A","1955/03/22","2010/05/24");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("27752982-4","Otto","HighTower","B","2001/11/18","2015/05/10");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("25752982-4","Laris","Strong","C","1995/11/11","2017/05/10");