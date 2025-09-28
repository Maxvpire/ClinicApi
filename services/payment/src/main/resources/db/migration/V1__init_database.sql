CREATE TABLE IF NOT EXISTS invoices (
    id TEXT PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id TEXT NOT NULL,
    appointment_id TEXT NOT NULL UNIQUE,
    amount DOUBLE PRECISION,
    status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO invoices (id, patient_id, appointment_id, amount, status, created_at, updated_at) VALUES
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 120.50, 'PENDING', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 250.00, 'PAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 99.99, 'UNPAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 300.75, 'PAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 450.25, 'PENDING', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 80.00, 'PAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 175.10, 'UNPAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 600.00, 'PENDING', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 320.40, 'PAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 210.00, 'PENDING', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 155.75, 'PAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 450.60, 'UNPAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 700.00, 'PENDING', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 90.99, 'PAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 310.25, 'PENDING', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 510.00, 'UNPAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 275.75, 'PAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 125.50, 'PENDING', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 400.30, 'PAID', NOW(), NOW()),
(gen_random_uuid()::text, gen_random_uuid()::text, gen_random_uuid()::text, 225.00, 'UNPAID', NOW(), NOW());
