DROP DATABASE IF EXISTS medicaldb;
CREATE DATABASE medicaldb;
CREATE USER 'patient'@'%' IDENTIFIED BY 'wso2';
GRANT ALL ON patient.* TO medicaldb@'%' IDENTIFIED BY 'wso2';
GRANT ALL ON patient.* TO medicaldb@'localhost' IDENTIFIED BY 'wso2';

USE medicaldb;
DROP TABLE IF EXISTS patient;
CREATE TABLE patient(patientNumber INT, patientLastName VARCHAR(20), patientFirstName VARCHAR(20), phone VARCHAR(20), city VARCHAR(20), streetname VARCHAR(20), country VARCHAR(20));


INSERT INTO patient (patientNumber, patientLastName, patientFirstName, phone, city, streetname, country) VALUES (0001, "Amarasiri", "Evanthika", "+94773636352", "Galle Road", "Colombo 03", "Sri Lanka");
INSERT INTO patient (patientNumber, patientLastName, patientFirstName, phone, city, streetname, country) VALUES (0002, "Fernando", "Senaka", "+94777112987", "Je-ela", "Temple Road", "Sri Lanka");
INSERT INTO patient (patientNumber, patientLastName, patientFirstName, phone, city, streetname, country) VALUES (0003, "Hiranya", "Hasitha", "+94771222922", "Kandy", "Radella", "Sri Lanka");
INSERT INTO patient (patientNumber, patientLastName, patientFirstName, phone, city, streetname, country) VALUES (0004, "Madurangi", "Pavithra", "+94772331922", "Horana", "6th Street", "Sri Lanka");
INSERT INTO patient (patientNumber, patientLastName, patientFirstName, phone, city, streetname, country) VALUES (0005, "Anuradha", "Chamara", "+94773113871", "Kegalle", "5th Lane", "Sri Lanka");
