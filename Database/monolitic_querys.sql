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
SELECT * FROM employee;
SELECT * FROM justificative;
SELECT * FROM extra_hours;
SELECT * FROM worked_days;

INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("28371904-9","nico","las","A","2000/03/22","2020/03/22");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("27391503-6","WEON","SOYYO","A","2000/03/22","2015/03/22");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("21142354-k","JUANITO","KIKA","C","2000/03/22","2010/03/22");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("12457562-3","JIJAJAJA","las","A","2000/03/22","2005/03/22");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("11234123-6","ROBERTO","CARLOS","B","1999/03/22","2000/03/22");
INSERT INTO employee(rut, `name`,last_name,category,birth_date,entry_date) VALUES("17765876-2","LOCO","RANDOM","B","1955/03/22","1999/03/22");
DELETE FROM employee WHERE id > 0;
DELETE FROM worked_days WHERE id > 0;
DELETE FROM extra_hours WHERE id > 0;
DELETE FROM justificative WHERE id > 0;
INSERT INTO `tingeso`.`worked_days`
(`rut_employee`,`date`,`extra_hours`,`late_minutes`)VALUES("27391503-6","2022-05-20",1,	0);

SELECT `date` FROM worked_days LIMIT 1;

SELECT * FROM extra_hours;
SELECT * FROM worked_days;	

SELECT eh.id, eh.rut_employee, eh.`date`,eh.n_hours FROM extra_hours AS eh
INNER JOIN worked_days AS wd ON (wd.rut_employee = eh.rut_employee AND wd.`date` = eh.`date`)
WHERE wd.extra_hours = eh.n_hours AND eh.rut_employee = "27391503-6" AND wd.rut_employee = "27391503-6";


-- Query para obtener meses que han sido trabajados
SELECT * FROM worked_days;
SELECT * FROM worked_days WHERE rut_employee = "27391503-6" AND `date` = "2022-01-18";
SELECT EXTRACT(MONTH FROM wd.`date`)
FROM worked_days AS wd
GROUP BY EXTRACT(YEAR FROM wd.`date`),EXTRACT(MONTH FROM wd.`date`);