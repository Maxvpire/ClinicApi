CREATE TYPE gender_enum AS ENUM ('MALE', 'FEMALE');

CREATE TABLE IF NOT EXISTS administration(
    id TEXT NOT NULL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    phone TEXT NOT NULL UNIQUE,
    avatar TEXT UNIQUE,
    gender gender_enum NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    deleted BOOLEAN DEFAULT FALSE,
    date_of_birth DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO administration (id, firstname, lastname, phone, gender, date_of_birth)
VALUES
    ('d3f1a8e2-9c4b-4a1f-8d2a-1f2c3b4e5a01', 'John', 'Doe', '+998901234001', 'MALE', '1985-06-15'),
    ('a7b2c3d4-e5f6-4a7b-8c9d-0e1f2a3b4c02', 'Jane', 'Smith', '+998901234002', 'FEMALE', '1990-03-22'),
    ('f1e2d3c4-b5a6-4c7d-8e9f-0a1b2c3d4e03', 'Aliya', 'Karimova', '+998901234003', 'FEMALE', '1988-11-05'),
    ('c3b4a5d6-e7f8-4a9b-8c0d-1e2f3a4b5c04', 'Timur', 'Bek', '+998901234004',  'MALE', '1979-01-30'),
    ('b2c3d4e5-a6f7-4b8c-9d0e-1f2a3b4c5d05', 'Sara', 'Lee', '+998901234005',  'FEMALE', '1995-07-19'),
    ('e1f2a3b4-c5d6-4e7f-8a9b-0c1d2e3f4a06', 'David', 'Kim', '+998901234006',  'MALE', '1982-12-12'),
    ('d4e5f6a7-b8c9-4d0e-9f1a-2b3c4d5e6f07', 'Nodira', 'Yusupova', '+998901234007',  'FEMALE', '1993-09-09'),
    ('a3b4c5d6-e7f8-4a9b-0c1d-2e3f4a5b6c08', 'Otabek', 'Rakhimov', '+998901234008',  'MALE', '1987-04-04'),
    ('f2e3d4c5-b6a7-4c8d-9e0f-1a2b3c4d5e09', 'Emily', 'Clark', '+998901234009',  'FEMALE', '1991-08-25'),
    ('c1d2e3f4-a5b6-4d7e-8f9a-0b1c2d3e4f10', 'Aziz', 'Tursunov', '+998901234010',  'MALE', '1980-10-10'),
    ('b3c4d5e6-f7a8-4b9c-0d1e-2f3a4b5c6d11', 'Layla', 'Hassan', '+998901234011',  'FEMALE', '1996-02-14'),
    ('e2f3a4b5-c6d7-4e8f-9a0b-1c2d3e4f5a12', 'Michael', 'Brown', '+998901234012',  'MALE', '1983-05-05'),
    ('d5e6f7a8-b9c0-4d1e-2f3a-4b5c6d7e8f13', 'Zarina', 'Khalilova', '+998901234013',  'FEMALE', '1994-06-06'),
    ('a4b5c6d7-e8f9-4a0b-1c2d-3e4f5a6b7c14', 'Jasur', 'Aliyev', '+998901234014',  'MALE', '1986-03-03'),
    ('f3a4b5c6-d7e8-4f9a-0b1c-2d3e4f5a6b15', 'Chloe', 'Nguyen', '+998901234015',  'FEMALE', '1992-12-01'),
    ('c2d3e4f5-a6b7-4d8e-9f0a-1b2c3d4e5f16', 'Rustam', 'Ismailov', '+998901234016',  'MALE', '1989-07-07'),
    ('b4c5d6e7-f8a9-4b0c-1d2e-3f4a5b6c7d17', 'Fatima', 'Saidova', '+998901234017',  'FEMALE', '1997-01-01'),
    ('e3f4a5b6-c7d8-4e9f-0a1b-2c3d4e5f6a18', 'Elyor', 'Kasimov', '+998901234018',  'MALE', '1984-09-15'),
    ('d6e7f8a9-b0c1-4d2e-3f4a-5b6c7d8e9f19', 'Anna', 'Petrova', '+998901234019',  'FEMALE', '1998-10-20'),
    ('a5b6c7d8-e9f0-4a1b-2c3d-4e5f6a7b8c20', 'Bekzod', 'Sharipov', '+998901234020',  'MALE', '1981-11-11');
