CREATE TYPE weekdays_enum AS ENUM ('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY');

CREATE TABLE IF NOT EXISTS doctor (
    id TEXT NOT NULL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone TEXT NOT NULL UNIQUE,
    avatar TEXT UNIQUE,
    gender TEXT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    dateofbirth DATE NOT NULL,
    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );


INSERT INTO doctor (id, firstname, lastname, specialization, email, phone, gender, dateOfBirth) VALUES
('a14f672d-994b-4c21-9bb8-f926e98e1731', 'Alice', 'Smith', 'Cardiology', 'alice.smith@hospital.com', '+998901111111', 'FEMALE', '1980-05-15'),
('38bb1765-92e2-4dc1-8cd1-9c5b9df62f41', 'Bob', 'Johnson', 'Neurology', 'bob.johnson@hospital.com', '+998901111112', 'MALE', '1975-08-20'),
('5074200c-b1a0-4044-8e7f-4f28d2ea9c4d', 'Clara', 'Lee', 'Pediatrics', 'clara.lee@hospital.com', '+998901111113', 'FEMALE', '1988-12-02'),
('9e4a6e69-07b5-49bc-8208-52cfef3a4d63', 'David', 'Brown', 'Dermatology', 'david.brown@hospital.com', '+998901111114', 'MALE', '1982-03-30'),
('f84c6e76-4f50-48d8-a030-6ff4ff3c9d64', 'Eva', 'Martinez', 'Orthopedics', 'eva.martinez@hospital.com', '+998901111115', 'FEMALE', '1990-10-10'),
('f0384d83-e934-4d91-80f9-8ff5401a0f2f', 'Frank', 'Miller', 'Urology', 'frank.miller@hospital.com', '+998901111116', 'MALE', '1979-04-18'),
('06150413-e4ea-470e-a9d2-11bb1fa2e12b', 'Grace', 'Wilson', 'Oncology', 'grace.wilson@hospital.com', '+998901111117', 'FEMALE', '1985-11-25'),
('96ad5121-71e1-4b2f-89f9-c8807a5c92cd', 'Henry', 'Davis', 'Radiology', 'henry.davis@hospital.com', '+998901111118', 'MALE', '1983-07-09'),
('2763b617-1051-4f64-b0bc-d5be57c7a1aa', 'Isla', 'Clark', 'Gastroenterology', 'isla.clark@hospital.com', '+998901111119', 'FEMALE', '1992-01-12'),
('e4e1ff29-0d2a-4099-bbd2-d790842d34c1', 'Jack', 'Lewis', 'Pathology', 'jack.lewis@hospital.com', '+998901111120', 'MALE', '1987-09-14'),
('53a58e2f-1d80-4472-9f52-e5d1a55eebc9', 'Kara', 'Walker', 'Anesthesiology', 'kara.walker@hospital.com', '+998901111121', 'FEMALE', '1981-06-08'),
('c9df1516-6583-4aa3-b2cd-509bba4c46a4', 'Leo', 'Hall', 'Nephrology', 'leo.hall@hospital.com', '+998901111122', 'MALE', '1976-12-21'),
('0091b4f4-5b3e-42ae-9c59-1d85f2546e83', 'Maya', 'Allen', 'Pulmonology', 'maya.allen@hospital.com', '+998901111123', 'FEMALE', '1989-03-04'),
('3fc6c718-8bfa-4bb7-9e65-3ebd3a3e9cf5', 'Noah', 'Young', 'Ophthalmology', 'noah.young@hospital.com', '+998901111124', 'MALE', '1991-08-17'),
('f9f139ec-f218-4e65-9fbd-8ebffb8c0f7e', 'Olivia', 'Hernandez', 'Psychiatry', 'olivia.hernandez@hospital.com', '+998901111125', 'FEMALE', '1984-02-28'),
('74f06bb5-9fd0-4d2f-80d7-2a6efb730ec8', 'Paul', 'King', 'Endocrinology', 'paul.king@hospital.com', '+998901111126', 'MALE', '1978-05-05'),
('1bb36c20-1637-47d1-8f01-187dbd15239e', 'Queenie', 'Wright', 'Hematology', 'queenie.wright@hospital.com', '+998901111127', 'FEMALE', '1993-04-16'),
('ba185bcb-8697-40f3-8983-d4c55c29b354', 'Ryan', 'Lopez', 'Rheumatology', 'ryan.lopez@hospital.com', '+998901111128', 'MALE', '1986-10-30'),
('b61cbdd9-d1fd-4952-86d2-5fdcb8d5fafe', 'Sophie', 'Scott', 'Immunology', 'sophie.scott@hospital.com', '+998901111129', 'FEMALE', '1990-06-22'),
('e6379cc5-e9ed-4722-90a2-404bde0164dc', 'Tom', 'Green', 'Infectious Disease', 'tom.green@hospital.com', '+998901111130', 'MALE', '1982-07-01');

CREATE TABLE IF NOT EXISTS schedule (
    id TEXT PRIMARY KEY,
    doctor_id TEXT NOT NULL,
    weekday TEXT NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_schedule_doctor FOREIGN KEY (doctor_id)
        REFERENCES doctor(id)
        ON DELETE CASCADE
);


INSERT INTO schedule (id, doctor_id, weekday, start_time, end_time, created_at, last_modified_at) VALUES
('f7ae11a5-1b3a-4f59-b291-1fd71a466ce5', 'a14f672d-994b-4c21-9bb8-f926e98e1731', 'MONDAY',    '08:00', '12:00', now(), now()),
('abe1327d-3016-4db7-9e1b-839cf2dbe00c', '38bb1765-92e2-4dc1-8cd1-9c5b9df62f41', 'TUESDAY',   '09:00', '13:00', now(), now()),
('983758b5-70a6-4eb6-b264-282560ba6d98', '5074200c-b1a0-4044-8e7f-4f28d2ea9c4d', 'WEDNESDAY', '08:30', '12:30', now(), now()),
('1e3195fa-9cb1-4209-a3ce-ec26e7c7b6c9', '9e4a6e69-07b5-49bc-8208-52cfef3a4d63', 'THURSDAY',  '10:00', '14:00', now(), now()),
('07ad5cf4-0f88-4e66-b5a6-9b7b6f0e3b75', 'f84c6e76-4f50-48d8-a030-6ff4ff3c9d64', 'FRIDAY',    '08:00', '11:00', now(), now()),
('f9dd8788-eab0-4e56-8c4b-13d016587eb1', 'f0384d83-e934-4d91-80f9-8ff5401a0f2f', 'MONDAY',    '14:00', '18:00', now(), now()),
('74a5de5b-e67f-4a8f-aec8-304e6f2e6c92', '06150413-e4ea-470e-a9d2-11bb1fa2e12b', 'TUESDAY',   '13:00', '17:00', now(), now()),
('50ec2d0d-3cf9-4e2e-bcfd-c88f46aa9620', '96ad5121-71e1-4b2f-89f9-c8807a5c92cd', 'WEDNESDAY', '07:00', '10:00', now(), now()),
('b2962ae4-9d6f-4d66-ae4f-82fd3c0b3d94', '2763b617-1051-4f64-b0bc-d5be57c7a1aa', 'THURSDAY',  '09:00', '12:00', now(), now()),
('95a67e93-d83f-4234-82e7-f5d345ab7b55', 'e4e1ff29-0d2a-4099-bbd2-d790842d34c1', 'FRIDAY',    '11:00', '15:00', now(), now()),
('a09c9f1d-d68f-496c-86b6-6c3af4d2b2a6', '53a58e2f-1d80-4472-9f52-e5d1a55eebc9', 'MONDAY',    '08:00', '10:00', now(), now()),
('245ca2de-faae-43e4-8ae6-7265a2c8f78c', 'c9df1516-6583-4aa3-b2cd-509bba4c46a4', 'TUESDAY',   '10:30', '13:30', now(), now()),
('d9f4f10c-fc74-4eaf-96d6-e3a8659d9ff2', '0091b4f4-5b3e-42ae-9c59-1d85f2546e83', 'WEDNESDAY', '12:00', '16:00', now(), now()),
('05302df8-2c2f-4081-967a-cc2f29494b8d', '3fc6c718-8bfa-4bb7-9e65-3ebd3a3e9cf5', 'THURSDAY',  '14:00', '18:00', now(), now()),
('726f9971-7c38-4ee8-9c0e-c3e723442037', 'f9f139ec-f218-4e65-9fbd-8ebffb8c0f7e', 'FRIDAY',    '09:00', '12:00', now(), now()),
('ad9a1d65-91e3-4e6b-94a4-9e5641c60921', '74f06bb5-9fd0-4d2f-80d7-2a6efb730ec8', 'MONDAY',    '10:00', '14:00', now(), now()),
('36f0f07a-084b-4200-afe8-e1c0a2f10366', '1bb36c20-1637-47d1-8f01-187dbd15239e', 'TUESDAY',   '11:00', '13:00', now(), now()),
('ce6bb37e-f788-4ad0-9218-859cb53dc6a1', 'ba185bcb-8697-40f3-8983-d4c55c29b354', 'WEDNESDAY', '15:00', '18:00', now(), now()),
('4e095147-ec10-4397-b01e-dfc18eb2384d', 'b61cbdd9-d1fd-4952-86d2-5fdcb8d5fafe', 'THURSDAY',  '08:00', '11:00', now(), now()),
('2c4c22f0-389d-4b2d-b84d-5e73c963c3cd', 'e6379cc5-e9ed-4722-90a2-404bde0164dc', 'FRIDAY',    '13:00', '17:00', now(), now());
