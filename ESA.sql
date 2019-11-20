--
-- File generated with SQLiteStudio v3.2.1 on Wed Nov 20 15:41:54 2019
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: EligibilityFactors
DROP TABLE IF EXISTS EligibilityFactors;

CREATE TABLE EligibilityFactors (
    Eligibility_Information_ID NUMERIC (5) PRIMARY KEY,
    Student_ID                 VARCHAR     REFERENCES Student (Student_ID),
    Served_In_Military         BOOLEAN,
    Military_Status            BOOLEAN,
    Active_Years               VARCHAR,
    Disability_Status          BOOLEAN,
    Financial_Aid_Eligibility  BOOLEAN,
    Residency_Status           VARCHAR,
    Years_of_Residency         VARCHAR,
    Dependent_Status           BOOLEAN
);

INSERT INTO EligibilityFactors (
                                   Eligibility_Information_ID,
                                   Student_ID,
                                   Served_In_Military,
                                   Military_Status,
                                   Active_Years,
                                   Disability_Status,
                                   Financial_Aid_Eligibility,
                                   Residency_Status,
                                   Years_of_Residency,
                                   Dependent_Status
                               )
                               VALUES (
                                   57893,
                                   '1023612',
                                   1,
                                   1,
                                   'Less than 1',
                                   1,
                                   0,
                                   'In-State',
                                   '10',
                                   0
                               );

INSERT INTO EligibilityFactors (
                                   Eligibility_Information_ID,
                                   Student_ID,
                                   Served_In_Military,
                                   Military_Status,
                                   Active_Years,
                                   Disability_Status,
                                   Financial_Aid_Eligibility,
                                   Residency_Status,
                                   Years_of_Residency,
                                   Dependent_Status
                               )
                               VALUES (
                                   56783,
                                   '1007859',
                                   0,
                                   NULL,
                                   NULL,
                                   0,
                                   1,
                                   'Out of State',
                                   '5',
                                   0
                               );

INSERT INTO EligibilityFactors (
                                   Eligibility_Information_ID,
                                   Student_ID,
                                   Served_In_Military,
                                   Military_Status,
                                   Active_Years,
                                   Disability_Status,
                                   Financial_Aid_Eligibility,
                                   Residency_Status,
                                   Years_of_Residency,
                                   Dependent_Status
                               )
                               VALUES (
                                   45678,
                                   '1018077',
                                   0,
                                   NULL,
                                   NULL,
                                   NULL,
                                   NULL,
                                   'Out of State',
                                   NULL,
                                   NULL
                               );

INSERT INTO EligibilityFactors (
                                   Eligibility_Information_ID,
                                   Student_ID,
                                   Served_In_Military,
                                   Military_Status,
                                   Active_Years,
                                   Disability_Status,
                                   Financial_Aid_Eligibility,
                                   Residency_Status,
                                   Years_of_Residency,
                                   Dependent_Status
                               )
                               VALUES (
                                   53126,
                                   '1000432',
                                   1,
                                   0,
                                   '11',
                                   0,
                                   0,
                                   'in-State',
                                   '20',
                                   NULL
                               );

INSERT INTO EligibilityFactors (
                                   Eligibility_Information_ID,
                                   Student_ID,
                                   Served_In_Military,
                                   Military_Status,
                                   Active_Years,
                                   Disability_Status,
                                   Financial_Aid_Eligibility,
                                   Residency_Status,
                                   Years_of_Residency,
                                   Dependent_Status
                               )
                               VALUES (
                                   67894,
                                   '1017696',
                                   0,
                                   NULL,
                                   NULL,
                                   NULL,
                                   NULL,
                                   NULL,
                                   NULL,
                                   NULL
                               );


-- Table: EnrollmentDecision
DROP TABLE IF EXISTS EnrollmentDecision;

CREATE TABLE EnrollmentDecision (
    Enrollment_ID     NUMERIC (5) PRIMARY KEY
                                  UNIQUE,
    Student_ID        VARCHAR (7) REFERENCES Student (Student_ID) 
                                  UNIQUE,
    Enrollment_Date   VARCHAR,
    Group_Number      NUMERIC,
    Group_Description BLOB
);

INSERT INTO EnrollmentDecision (
                                   Enrollment_ID,
                                   Student_ID,
                                   Enrollment_Date,
                                   Group_Number,
                                   Group_Description
                               )
                               VALUES (
                                   34789,
                                   '1023612',
                                   '10/15/2019',
                                   2,
                                   'Group 2: USA Resident, military service from 1 to 5 years, or disability = yes. Scholarship award 75%. '
                               );

INSERT INTO EnrollmentDecision (
                                   Enrollment_ID,
                                   Student_ID,
                                   Enrollment_Date,
                                   Group_Number,
                                   Group_Description
                               )
                               VALUES (
                                   12345,
                                   '1007859',
                                   '11/11/2019',
                                   3,
                                   'Group 3: Non-dependent with financial aid eligibility. Scholarship award 50%'
                               );

INSERT INTO EnrollmentDecision (
                                   Enrollment_ID,
                                   Student_ID,
                                   Enrollment_Date,
                                   Group_Number,
                                   Group_Description
                               )
                               VALUES (
                                   56783,
                                   '1018077',
                                   '08/14/2018',
                                   4,
                                   'Group 4: Dependent but from low income family.  Scholarship award 35%.'
                               );

INSERT INTO EnrollmentDecision (
                                   Enrollment_ID,
                                   Student_ID,
                                   Enrollment_Date,
                                   Group_Number,
                                   Group_Description
                               )
                               VALUES (
                                   98567,
                                   '1000432',
                                   '9/1/2019',
                                   1,
                                   'Group 1: USA Resident, military service is more than 5 years, or senior In-State. Tuition is free.'
                               );

INSERT INTO EnrollmentDecision (
                                   Enrollment_ID,
                                   Student_ID,
                                   Enrollment_Date,
                                   Group_Number,
                                   Group_Description
                               )
                               VALUES (
                                   67894,
                                   '1017696',
                                   '11/10/2019',
                                   5,
                                   'Group 5: Military service is less than a year, 529 account, not from low income family, or other categories not eligible for discount. Not eligible for scholarship.'
                               );


-- Table: FinancialInformation
DROP TABLE IF EXISTS FinancialInformation;

CREATE TABLE FinancialInformation (
    Financial_Information_ID   NUMERIC (5) PRIMARY KEY
                                           UNIQUE,
    Student_ID                 VARCHAR     REFERENCES Student (Student_ID) 
                                           UNIQUE
                                           NOT NULL,
    Financially_Depended       BOOLEAN,
    Student_Last_Year_Income   DOUBLE,
    [Parent_Last_ Year_Income] DOUBLE,
    Own_529_Account            BOOLEAN,
    Own_Real_Estate_Land       BOOLEAN,
    Value_Of_Other_Properties  DOUBLE
);

INSERT INTO FinancialInformation (
                                     Financial_Information_ID,
                                     Student_ID,
                                     Financially_Depended,
                                     Student_Last_Year_Income,
                                     [Parent_Last_ Year_Income],
                                     Own_529_Account,
                                     Own_Real_Estate_Land,
                                     Value_Of_Other_Properties
                                 )
                                 VALUES (
                                     10345,
                                     '1023612',
                                     1,
                                     45000.0,
                                     0.0,
                                     0,
                                     0,
                                     0.0
                                 );

INSERT INTO FinancialInformation (
                                     Financial_Information_ID,
                                     Student_ID,
                                     Financially_Depended,
                                     Student_Last_Year_Income,
                                     [Parent_Last_ Year_Income],
                                     Own_529_Account,
                                     Own_Real_Estate_Land,
                                     Value_Of_Other_Properties
                                 )
                                 VALUES (
                                     43267,
                                     '1007859',
                                     0,
                                     22000.0,
                                     NULL,
                                     0,
                                     0,
                                     5000.0
                                 );

INSERT INTO FinancialInformation (
                                     Financial_Information_ID,
                                     Student_ID,
                                     Financially_Depended,
                                     Student_Last_Year_Income,
                                     [Parent_Last_ Year_Income],
                                     Own_529_Account,
                                     Own_Real_Estate_Land,
                                     Value_Of_Other_Properties
                                 )
                                 VALUES (
                                     67893,
                                     '1017696',
                                     1,
                                     0.0,
                                     78000.0,
                                     1,
                                     0,
                                     0.0
                                 );

INSERT INTO FinancialInformation (
                                     Financial_Information_ID,
                                     Student_ID,
                                     Financially_Depended,
                                     Student_Last_Year_Income,
                                     [Parent_Last_ Year_Income],
                                     Own_529_Account,
                                     Own_Real_Estate_Land,
                                     Value_Of_Other_Properties
                                 )
                                 VALUES (
                                     78903,
                                     '1000432',
                                     0,
                                     65000.0,
                                     0.0,
                                     0,
                                     0,
                                     0.0
                                 );

INSERT INTO FinancialInformation (
                                     Financial_Information_ID,
                                     Student_ID,
                                     Financially_Depended,
                                     Student_Last_Year_Income,
                                     [Parent_Last_ Year_Income],
                                     Own_529_Account,
                                     Own_Real_Estate_Land,
                                     Value_Of_Other_Properties
                                 )
                                 VALUES (
                                     57823,
                                     '1018077',
                                     1,
                                     0.0,
                                     15000.0,
                                     NULL,
                                     1,
                                     0.0
                                 );


-- Table: Student
DROP TABLE IF EXISTS Student;

CREATE TABLE Student (
    Student_ID        VARCHAR (7)  PRIMARY KEY
                                   UNIQUE
                                   NOT NULL,
    SSN               VARCHAR (9)  UNIQUE
                                   NOT NULL,
    Last_Name         VARCHAR (15) NOT NULL,
    First_Name        VARCHAR (15) NOT NULL,
    DOB               VARCHAR      NOT NULL,
    Birth_Sex         VARCHAR      NOT NULL,
    Emergency_Contact VARCHAR (35),
    Emergency_Phone   VARCHAR (10),
    Student_Street    VARCHAR (50),
    Student_City      VARCHAR (25),
    Student_State     VARCHAR (2),
    Student_Zip       VARCHAR,
    USA_Resident      BOOLEAN,
    Student_Phone     VARCHAR (10) 
);

INSERT INTO Student (
                        Student_ID,
                        SSN,
                        Last_Name,
                        First_Name,
                        DOB,
                        Birth_Sex,
                        Emergency_Contact,
                        Emergency_Phone,
                        Student_Street,
                        Student_City,
                        Student_State,
                        Student_Zip,
                        USA_Resident,
                        Student_Phone
                    )
                    VALUES (
                        '1023612',
                        '014028586',
                        'rsausaoaur',
                        'John',
                        '11/15/1985',
                        'male',
                        'Test Emergency',
                        '3017843215',
                        '123 Anywher St',
                        'Rockville',
                        'MD',
                        '20850',
                        'Yes',
                        '3018974315'
                    );

INSERT INTO Student (
                        Student_ID,
                        SSN,
                        Last_Name,
                        First_Name,
                        DOB,
                        Birth_Sex,
                        Emergency_Contact,
                        Emergency_Phone,
                        Student_Street,
                        Student_City,
                        Student_State,
                        Student_Zip,
                        USA_Resident,
                        Student_Phone
                    )
                    VALUES (
                        '1007859',
                        '250498510',
                        'kruahetkpajzkqgd',
                        'Anna',
                        '11/05/1990',
                        'female',
                        'Testtwo Emerg',
                        '4510023915',
                        '401 Test Cr.',
                        'Ashton',
                        'VA',
                        '60216',
                        'Yes',
                        NULL
                    );

INSERT INTO Student (
                        Student_ID,
                        SSN,
                        Last_Name,
                        First_Name,
                        DOB,
                        Birth_Sex,
                        Emergency_Contact,
                        Emergency_Phone,
                        Student_Street,
                        Student_City,
                        Student_State,
                        Student_Zip,
                        USA_Resident,
                        Student_Phone
                    )
                    VALUES (
                        '1018077',
                        '420932923',
                        'sycuozkqtgfj',
                        'Alex',
                        '11/15/2001',
                        'male',
                        'Testthree Otierhg',
                        '4110029270',
                        '202 Wayne Ave',
                        'Denver',
                        'CO',
                        '67216',
                        'Yes',
                        '2407853214'
                    );

INSERT INTO Student (
                        Student_ID,
                        SSN,
                        Last_Name,
                        First_Name,
                        DOB,
                        Birth_Sex,
                        Emergency_Contact,
                        Emergency_Phone,
                        Student_Street,
                        Student_City,
                        Student_State,
                        Student_Zip,
                        USA_Resident,
                        Student_Phone
                    )
                    VALUES (
                        '1000432',
                        '208091064',
                        'lxxkuco',
                        'Alex',
                        '10/20/1990',
                        'female',
                        'Testfour Gkljkl',
                        '3110004129',
                        '123 First St',
                        'Gaithersburg',
                        'MD',
                        '20877',
                        'Yes',
                        NULL
                    );

INSERT INTO Student (
                        Student_ID,
                        SSN,
                        Last_Name,
                        First_Name,
                        DOB,
                        Birth_Sex,
                        Emergency_Contact,
                        Emergency_Phone,
                        Student_Street,
                        Student_City,
                        Student_State,
                        Student_Zip,
                        USA_Resident,
                        Student_Phone
                    )
                    VALUES (
                        '1017696',
                        '821963861',
                        'onewkkfrhjlajss',
                        'Peter',
                        '10/12/2001',
                        'male',
                        'Testfive Uiklddf',
                        '5110012169',
                        '101 Second Ct',
                        'Cocoa Beach',
                        'FL',
                        '12672',
                        'Yes',
                        NULL
                    );


-- Index: 
DROP INDEX IF EXISTS "";

CREATE UNIQUE INDEX "" ON Student (
    Student_ID DESC
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
