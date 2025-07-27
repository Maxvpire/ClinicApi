CREATE TYPE gender_enum AS ENUM ('MALE', 'FEMALE');

CREATE TABLE IF NOT EXISTS doctor (
    id TEXT NOT NULL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    email TEXT NOT NULL UNIQUE,
    gender gender_enum NOT NULL,
    dateOfBirth DATE NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );


INSERT INTO doctor (id, firstname, lastname, specialization, email, gender, dateOfBirth) VALUES
('b6a6f51e-9f2a-4b13-81a5-0bfa6dc5a1f0', 'Alice', 'Smith', 'Cardiology', 'alice.smith@hospital.com', 'FEMALE', '1980-05-15'),
('f3d0a7c1-36f9-4d52-bc75-87bbf6316b71', 'Bob', 'Johnson', 'Neurology', 'bob.johnson@hospital.com', 'MALE', '1975-08-20'),
('d9e0c839-5e44-48b3-98e3-8b83dfb8d0f3', 'Clara', 'Lee', 'Pediatrics', 'clara.lee@hospital.com', 'FEMALE', '1988-12-02'),
('88cfe1cb-fb36-4db4-a7ea-1d1db4059c0c', 'David', 'Brown', 'Dermatology', 'david.brown@hospital.com', 'MALE', '1982-03-30'),
('2d1935dc-c55c-4b34-a8f3-449b9c2c22e4', 'Eva', 'Martinez', 'Orthopedics', 'eva.martinez@hospital.com', 'FEMALE', '1990-10-10'),
('a2d90ed7-8b35-4767-938f-6015cb5c452a', 'Frank', 'Miller', 'Urology', 'frank.miller@hospital.com', 'MALE', '1979-04-18'),
('f8401c3d-27c1-4eb6-83f8-fb4b52c73df6', 'Grace', 'Wilson', 'Oncology', 'grace.wilson@hospital.com', 'FEMALE', '1985-11-25'),
('cd05eab3-991d-4729-8583-4b79dbf487ac', 'Henry', 'Davis', 'Radiology', 'henry.davis@hospital.com', 'MALE', '1983-07-09'),
('7cf9ab1a-6465-4a1a-a263-f1d2c45b8709', 'Isla', 'Clark', 'Gastroenterology', 'isla.clark@hospital.com', 'FEMALE', '1992-01-12'),
('a9817093-7d88-4a7c-86ec-efc3799ff999', 'Jack', 'Lewis', 'Pathology', 'jack.lewis@hospital.com', 'MALE', '1987-09-14'),
('9dfec015-44f6-470d-ae30-ff450e7c0cb2', 'Kara', 'Walker', 'Anesthesiology', 'kara.walker@hospital.com', 'FEMALE', '1981-06-08'),
('c7bcf530-42cb-46d1-b1a2-250ca9bdf9d2', 'Leo', 'Hall', 'Nephrology', 'leo.hall@hospital.com', 'MALE', '1976-12-21'),
('cc5f54e2-c054-4ad1-8727-df6c3a3ee703', 'Maya', 'Allen', 'Pulmonology', 'maya.allen@hospital.com', 'FEMALE', '1989-03-04'),
('1fa3304c-3f64-42a6-a9b1-88e60f1fe3b1', 'Noah', 'Young', 'Ophthalmology', 'noah.young@hospital.com', 'MALE', '1991-08-17'),
('221cfce5-dc6a-41c7-b87e-6e78cba02b2e', 'Olivia', 'Hernandez', 'Psychiatry', 'olivia.hernandez@hospital.com', 'FEMALE', '1984-02-28'),
('3df1aeb6-c13b-401f-ae6b-9e8fc264e6f7', 'Paul', 'King', 'Endocrinology', 'paul.king@hospital.com', 'MALE', '1978-05-05'),
('58cf3d0b-bc12-4295-91aa-95edaf1e469e', 'Queenie', 'Wright', 'Hematology', 'queenie.wright@hospital.com', 'FEMALE', '1993-04-16'),
('99d92ee8-510b-4e82-97e4-cd8f87138355', 'Ryan', 'Lopez', 'Rheumatology', 'ryan.lopez@hospital.com', 'MALE', '1986-10-30'),
('ff5ad865-1d5e-46e5-bbb7-e68ec8f1456a', 'Sophie', 'Scott', 'Immunology', 'sophie.scott@hospital.com', 'FEMALE', '1990-06-22'),
('cfc8d3c0-1977-4b89-98cd-3fbc7557a8b3', 'Tom', 'Green', 'Infectious Disease', 'tom.green@hospital.com', 'MALE', '1982-07-01');