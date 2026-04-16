CREATE TABLE invoices (
                          id BIGSERIAL PRIMARY KEY,
                          invoice_number VARCHAR(255) UNIQUE NOT NULL,
                          amount DECIMAL(19, 2) NOT NULL,
                          issue_date DATE,
                          status VARCHAR(50) NOT NULL,
                          customer_id BIGINT NOT NULL,
                          CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers (id)
);