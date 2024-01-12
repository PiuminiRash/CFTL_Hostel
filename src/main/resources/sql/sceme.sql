drop database if EXISTS HostalEducation;
create database HostalEducation;

USE HostalEducation;

CREATE TABLE Users (
                       userName VARCHAR(100)PRIMARY KEY NOT NULL ,
                       password VARCHAR(100)NOT NULL ,
                       email VARCHAR(200)NOT NULL
);

CREATE TABLE Staff(
                      StaffType VARCHAR(100) NOT NULL ,
                      StaffId VARCHAR(100) PRIMARY KEY ,
                      StaffName VARCHAR(200) NOT NULL ,
                      ContactNo INT (10) NOT NULL ,
                      NIC VARCHAR(200) NOT NULL ,
                      Email VARCHAR(200) NOT NULL
);

CREATE TABLE Section(
                      SectionName VARCHAR(200) PRIMARY KEY
);

CREATE TABLE Bucket(
                       BucketId VARCHAR(100)PRIMARY KEY ,
                       BucketName VARCHAR(100)
);

CREATE TABLE Income (
                        Code INT PRIMARY KEY AUTO_INCREMENT,
                        Description VARCHAR(100) NOT NULL,
                        Amount DOUBLE NOT NULL,
                        Year INT NOT NULL,
                        Month VARCHAR(20),
                        Date DATE
);

CREATE TABLE Expenditure (
                             Code INT PRIMARY KEY AUTO_INCREMENT,
                             Description VARCHAR(100) NOT NULL,
                             Amount DOUBLE NOT NULL,
                             Year INT NOT NULL,
                             Month VARCHAR(20),
                             Date DATE
);

CREATE TABLE Room(
                     RoomNo VARCHAR(100) PRIMARY KEY ,
                     RoomName VARCHAR(100) NOT NULL ,
                     NoOfBeds INT NOT NULL
);

CREATE TABLE Timetable(
                          SectionName VARCHAR(200),
                          Monday VARCHAR(100),
                          Tuesday VARCHAR(100),
                          Wednesday VARCHAR(100),
                          Thursday VARCHAR(100),
                          Friday VARCHAR(100),
                          SateDay VARCHAR(100),
                          Sunday VARCHAR(100),
                          CONSTRAINT FOREIGN KEY (SectionName) REFERENCES Section (SectionName) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Salary(
                       SalaryId INT PRIMARY KEY AUTO_INCREMENT,
                       Type VARCHAR(100)NOT NULL ,
                       Month VARCHAR(100)NOT NULL ,
                       StaffId VARCHAR(100),
                       SalaryAmt DOUBLE NOT NULL ,
                       Bonus DOUBLE NOT NULL ,
                       E DOUBLE NOT NULL ,
                       FinalSalary DOUBLE NOT NULL,
                       CONSTRAINT FOREIGN KEY (StaffId) REFERENCES Staff(StaffId) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Attendance(
                           AttendanceId INT PRIMARY KEY AUTO_INCREMENT,
                           Date DATE,
                           StaffId VARCHAR(100)NOT NULL ,
                           CONSTRAINT FOREIGN KEY (StaffId) REFERENCES Staff(StaffId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Student(
                        StudentId VARCHAR(100)PRIMARY KEY ,
                        StudentName VARCHAR(200)NOT NULL ,
                        Address VARCHAR(200)NOT NULL ,
                        Section VARCHAR(100)NOT NULL ,
                        Bucket1 VARCHAR(200),
                        Bucket2 VARCHAR(200),
                        Bucket3 VARCHAR(200),
                        RoomNo VARCHAR(100),
                        CONSTRAINT FOREIGN KEY (Section) REFERENCES Section(SectionName) ON DELETE CASCADE ON UPDATE CASCADE,
                        CONSTRAINT FOREIGN KEY (RoomNo) REFERENCES Room(RoomNo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Gardian(
                             StudentId VARCHAR (100) ,
                             GardianName VARCHAR(200) NOT NULL ,
                             Email VARCHAR(300) NOT NULL ,
                             ContactNo VARCHAR(10) NOT NULL ,
                             CONSTRAINT FOREIGN KEY (StudentId) REFERENCES Student (StudentId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Payment (
                         PaymentId INT AUTO_INCREMENT PRIMARY KEY,
                         Month VARCHAR(100) NOT NULL,
                         Date DATE NOT NULL,
                         StudentId VARCHAR(100),
                         StudentName VARCHAR(200),
                         Amount DOUBLE NOT NULL ,
                         CONSTRAINT FOREIGN KEY(StudentId) REFERENCES Student(StudentId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Subject(
                        SubjectCode VARCHAR(100)PRIMARY KEY ,
                        SubjectName VARCHAR(250)NOT NULL ,
                        Bucket VARCHAR(100)
);

CREATE TABLE SubjectDetails(
                               SubjectCode VARCHAR(100),
                               StaffId VARCHAR(100),
                               CONSTRAINT FOREIGN KEY (SubjectCode) REFERENCES Subject (SubjectCode) ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT FOREIGN KEY (StaffId) REFERENCES Staff (StaffId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE SectionDetails(
                               SectionName VARCHAR(200),
                               SubjectCode VARCHAR(100),
                               CONSTRAINT FOREIGN KEY (SectionName) REFERENCES Section (SectionName) ON UPDATE CASCADE ON DELETE CASCADE,
                               CONSTRAINT FOREIGN KEY (SubjectCode) REFERENCES Subject (SubjectCode) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO Section VALUES ('Science');
INSERT INTO Section VALUES ('Maths');
INSERT INTO Section VALUES ('Tec');
INSERT INTO Section VALUES ('Art');
INSERT INTO Section VALUES ('Commerce');

INSERT INTO Bucket VALUES ('Bucket1','1 st Bucket');
INSERT INTO Bucket VALUES ('Bucket2','2 st Bucket');
INSERT INTO Bucket VALUES ('Bucket3','3 st Bucket');

INSERT INTO Timetable VALUES ('Art','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy');
INSERT INTO Timetable VALUES ('Science','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy');
INSERT INTO Timetable VALUES ('Commerce','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy');
INSERT INTO Timetable VALUES ('Maths','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy');
INSERT INTO Timetable VALUES ('Tec','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy','SelfStudy');

