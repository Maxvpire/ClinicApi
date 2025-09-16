CREATE TABLE IF NOT EXISTS users(
                                    id TEXT NOT NULL PRIMARY KEY,
                                    username TEXT NOT NULL UNIQUE,
                                    password TEXT NOT NULL,
                                    main_id TEXT NOT NULL UNIQUE,
                                    user_role TEXT NOT NULL,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (id, username, password, main_id, user_role)
VALUES
    ('1a2b3c4d-1111-2222-3333-444455556666', '998901234567', '998901234567', 'u001', 'ADMINISTRATION'),
    ('2b3c4d5e-2222-3333-4444-555566667777', '998902345678', '998902345678', 'u002', 'PATIENT'),
    ('3c4d5e6f-3333-4444-5555-666677778888', '998903456789', '998903456789', 'u003', 'DOCTOR'),
    ('4d5e6f7g-4444-5555-6666-777788889999', '998904567890', '998904567890', 'u004', 'PATIENT'),
    ('5e6f7g8h-5555-6666-7777-888899990000', '998905678901', '998905678901', 'u005', 'DOCTOR');
