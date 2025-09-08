CREATE TABLE IF NOT EXISTS rates(
    id TEXT NOT NULL PRIMARY KEY,
    appointment_id TEXT NOT NULL UNIQUE,
    patient_id TEXT NOT NULL,
    doctor_id TEXT NOT NULL,
    rating DOUBLE PRECISION,
    comment TEXT,
    rated BOOLEAN DEFAULT FALSE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO rates (id, appointment_id, patient_id, doctor_id, rating, comment, rated, created_at, updated_at)
VALUES ('fd6d9fd2-bf19-46b5-bcb6-2ee3ac69582b', 'bbbf16d9-3c02-4d33-979b-9eb1c66fed37', '688798a4576053156e781bed', 'a14f672d-994b-4c21-9bb8-f926e98e1731', 1.0, 'Sample comment 1', TRUE, '2025-09-07 07:19:53', '2025-09-07 07:19:53');

INSERT INTO rates (id, appointment_id, patient_id, doctor_id, rating, comment, rated, created_at, updated_at)
VALUES ('c5c28ea8-3472-4cf5-99eb-cf8e74b3e27b', '262cf0d2-2205-4197-8701-2857413c724a', '688798a4576053156e781bed', 'a14f672d-994b-4c21-9bb8-f926e98e1731', 2.1, 'Sample comment 2', TRUE, '2025-09-07 07:19:53', '2025-09-07 07:19:53');

INSERT INTO rates (id, appointment_id, patient_id, doctor_id, rating, comment, rated, created_at, updated_at)
VALUES ('e99417f6-0b54-49d1-8468-6a54fe1e3e5d', '8e873567-dfbf-40f5-bf2c-9c4a2d84f990', '688798a4576053156e781bed', 'a14f672d-994b-4c21-9bb8-f926e98e1731', 3.2, 'Sample comment 3', TRUE, '2025-09-07 07:19:53', '2025-09-07 07:19:53');

INSERT INTO rates (id, appointment_id, patient_id, doctor_id, rating, comment, rated, created_at, updated_at)
VALUES ('99269b5e-29b1-4ed1-af45-d72a28ea2623', 'c4c63088-2ccf-44fc-ad8c-216f2bdd367d', '688798a4576053156e781bed', 'a14f672d-994b-4c21-9bb8-f926e98e1731', 4.0, 'Sample comment 4', TRUE, '2025-09-07 07:19:53', '2025-09-07 07:19:53');

INSERT INTO rates (id, appointment_id, patient_id, doctor_id, rating, comment, rated, created_at, updated_at)
VALUES ('f4accf38-59e8-448f-87df-078cb687e06f', 'd5844315-52aa-444a-9fff-f781ff91b2a2', '688798a4576053156e781bed', 'a14f672d-994b-4c21-9bb8-f926e98e1731', 5.1, 'Sample comment 5', TRUE, '2025-09-07 07:19:53', '2025-09-07 07:19:53');

INSERT INTO rates (id, appointment_id, patient_id, doctor_id, rating, comment, rated, created_at, updated_at)
VALUES ('bb23266f-99e2-4998-955b-9954e0fd4a9f', 'ce440d3c-1304-490f-ad70-423f7a2eddd4', '688798a4576053156e781bed', 'a14f672d-994b-4c21-9bb8-f926e98e1731', 1.2, 'Sample comment 6', TRUE, '2025-09-07 07:19:53', '2025-09-07 07:19:53');

INSERT INTO rates (id, appointment_id, patient_id, doctor_id, rating, comment, rated, created_at, updated_at)
VALUES ('1fc90db7-80a3-41b5-87c0-cb64f046f996', 'f0be8654-094e-4525-9a6e-100a57507c6f', '688798a4576053156e781bed', 'a14f672d-994b-4c21-9bb8-f926e98e1731', 2.0, 'Sample comment 7', TRUE, '2025-09-07 07:19:53', '2025-09-07 07:19:53');

INSERT INTO rates (id, appointment_id, patient_id, doctor_id, rating, comment, rated, created_at, updated_at)
VALUES ('aad07fe0-97a2-47e7-bd64-6127ee7283da', 'fb314707-a9be-46da-92d5-cdd8e5b592ca', '688798a4576053156e781bed', 'a14f672d-994b-4c21-9bb8-f926e98e1731', 3.1, 'Sample comment 8', TRUE, '2025-09-07 07:19:53', '2025-09-07 07:19:53');