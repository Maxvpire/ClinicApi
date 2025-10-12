CREATE TABLE invoices (
                          id TEXT PRIMARY KEY,
                          patient_id TEXT NOT NULL,
                          appointment_id TEXT UNIQUE NOT NULL,
                          amount NUMERIC(15,2),
                          status TEXT NOT NULL, -- storing enum as plain text
                          created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                          updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE payment (
                         id TEXT PRIMARY KEY,
                         amount NUMERIC(15,2),
                         method TEXT, -- PaymentMethod enum stored as text
                         invoices_id TEXT UNIQUE, -- One-to-One with invoices
                         created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         CONSTRAINT fk_payment_invoice FOREIGN KEY (invoices_id) REFERENCES invoices(id) ON DELETE CASCADE
);
