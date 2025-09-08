CREATE TABLE IF NOT EXISTS medical_records(
    id TEXT NOT NULL PRIMARY KEY,
    patient_id TEXT NOT NULL,
    doctor_id TEXT NOT NULL,
    appointment_id TEXT NOT NULL UNIQUE,
    diagnosis TEXT,
    treatment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS prescriptions(
    id TEXT NOT NULL PRIMARY KEY,
    medical_record_id TEXT NOT NULL,
    medication_name TEXT NOT NULL,
    dosage TEXT NOT NULL,
    frequency TEXT NOT NULL,
    duration TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_medical_record
    FOREIGN KEY (medical_record_id) REFERENCES medical_records(id) ON DELETE CASCADE
);


INSERT INTO medical_records (id, patient_id, doctor_id, appointment_id, diagnosis, treatment) VALUES
('11111111-1111-1111-1111-111111111111', 'a1a1a1a1-a1a1-a1a1-a1a1-a1a1a1a1a1a1', 'd1d1d1d1-d1d1-d1d1-d1d1-d1d1d1d1d1d1', 'p1p1p1p1-p1p1-p1p1-p1p1-p1p1p1p1p1p1', 'Flu', 'Rest, hydration, paracetamol'),
('22222222-2222-2222-2222-222222222222', 'a2a2a2a2-a2a2-a2a2-a2a2-a2a2a2a2a2a2', 'd2d2d2d2-d2d2-d2d2-d2d2-d2d2d2d2d2d2', 'p2p2p2p2-p2p2-p2p2-p2p2-p2p2p2p2p2p2', 'Hypertension', 'Antihypertensives, low-salt diet'),
('33333333-3333-3333-3333-333333333333', 'a3a3a3a3-a3a3-a3a3-a3a3-a3a3a3a3a3a3', 'd3d3d3d3-d3d3-d3d3-d3d3-d3d3d3d3d3d3', 'p3p3p3p3-p3p3-p3p3-p3p3-p3p3p3p3p3p3', 'Diabetes Type 2', 'Insulin, diet modification'),
('44444444-4444-4444-4444-444444444444', 'a4a4a4a4-a4a4-a4a4-a4a4-a4a4a4a4a4a4', 'd4d4d4d4-d4d4-d4d4-d4d4-d4d4d4d4d4d4', 'p4p4p4p4-p4p4-p4p4-p4p4-p4p4p4p4p4p4', 'Asthma', 'Inhaler, avoid triggers'),
('55555555-5555-5555-5555-555555555555', 'a5a5a5a5-a5a5-a5a5-a5a5-a5a5a5a5a5a5', 'd5d5d5d5-d5d5-d5d5-d5d5-d5d5d5d5d5d5', 'p5p5p5p5-p5p5-p5p5-p5p5-p5p5p5p5p5p5', 'Migraine', 'Painkillers, rest'),
('66666666-6666-6666-6666-666666666666', 'a6a6a6a6-a6a6-a6a6-a6a6-a6a6a6a6a6a6', 'd6d6d6d6-d6d6-d6d6-d6d6-d6d6d6d6d6d6', 'p6p6p6p6-p6p6-p6p6-p6p6-p6p6p6p6p6p6', 'Allergy', 'Antihistamines'),
('77777777-7777-7777-7777-777777777777', 'a7a7a7a7-a7a7-a7a7-a7a7-a7a7a7a7a7a7', 'd7d7d7d7-d7d7-d7d7-d7d7-d7d7d7d7d7d7', 'p7p7p7p7-p7p7-p7p7-p7p7-p7p7p7p7p7p7', 'COVID-19', 'Antivirals, supportive care'),
('88888888-8888-8888-8888-888888888888', 'a8a8a8a8-a8a8-a8a8-a8a8-a8a8a8a8a8a8', 'd8d8d8d8-d8d8-d8d8-d8d8-d8d8d8d8d8d8', 'p8p8p8p8-p8p8-p8p8-p8p8-p8p8p8p8p8p8', 'Back Pain', 'Physiotherapy, pain relief'),
('99999999-9999-9999-9999-999999999999', 'a9a9a9a9-a9a9-a9a9-a9a9-a9a9a9a9a9a9', 'd9d9d9d9-d9d9-d9d9-d9d9-d9d9d9d9d9d9', 'p9p9p9p9-p9p9-p9p9-p9p9-p9p9p9p9p9p9', 'Bronchitis', 'Antibiotics, rest'),
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'aaaa1111-aaaa-aaaa-aaaa-aaaaaaaa1111', 'dddd1111-dddd-dddd-dddd-dddddddd1111', 'pppp1111-pppp-pppp-pppp-pppppppp1111', 'Stomach Ulcer', 'Proton pump inhibitors');

INSERT INTO prescriptions (id, medical_record_id, medication_name, dosage, frequency, duration) VALUES
('p1111111-1111-1111-1111-111111111111', '11111111-1111-1111-1111-111111111111', 'Paracetamol', '500mg', '3 times/day', '5 days'),
('p2222222-2222-2222-2222-222222222222', '22222222-2222-2222-2222-222222222222', 'Amlodipine', '10mg', 'Once daily', '30 days'),
('p3333333-3333-3333-3333-333333333333', '33333333-3333-3333-3333-333333333333', 'Insulin Glargine', '20 units', 'Once daily', 'Indefinite'),
('p4444444-4444-4444-4444-444444444444', '44444444-4444-4444-4444-444444444444', 'Salbutamol Inhaler', '2 puffs', 'As needed', '6 months'),
('p5555555-5555-5555-5555-555555555555', '55555555-5555-5555-5555-555555555555', 'Ibuprofen', '400mg', 'Twice daily', '7 days'),
('p6666666-6666-6666-6666-666666666666', '66666666-6666-6666-6666-666666666666', 'Cetirizine', '10mg', 'Once daily', '14 days'),
('p7777777-7777-7777-7777-777777777777', '77777777-7777-7777-7777-777777777777', 'Favipiravir', '200mg', 'Twice daily', '10 days'),
('p8888888-8888-8888-8888-888888888888', '88888888-8888-8888-8888-888888888888', 'Naproxen', '500mg', 'Twice daily', '14 days'),
('p9999999-9999-9999-9999-999999999999', '99999999-9999-9999-9999-999999999999', 'Amoxicillin', '500mg', '3 times/day', '7 days'),
('paaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Omeprazole', '20mg', 'Once daily', '8 weeks');

