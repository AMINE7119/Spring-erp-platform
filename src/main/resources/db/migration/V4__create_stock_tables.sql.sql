-- 1. Table des produits
CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          reference VARCHAR(255) NOT NULL UNIQUE,
                          name VARCHAR(255) NOT NULL,
                          price NUMERIC(38,2) NOT NULL,
                          current_quantity INTEGER NOT NULL DEFAULT 0
);

-- 2. Table de l'historique des mouvements de stock
CREATE TABLE stock_movements (
                                 id BIGSERIAL PRIMARY KEY,
                                 product_id BIGINT NOT NULL,
                                 movement_type VARCHAR(50) NOT NULL CHECK (movement_type IN ('IN', 'OUT')),
                                 quantity INTEGER NOT NULL,
                                 movement_date TIMESTAMP NOT NULL,
                                 reason VARCHAR(255),
                                 CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id)
);