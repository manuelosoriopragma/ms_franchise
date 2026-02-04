CREATE TABLE IF NOT EXISTS franchises (
    id BIGSERIAL PRIMARY KEY, -- Coincide con Long en Java
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS branches (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    franchise_id BIGINT NOT NULL,
    CONSTRAINT fk_franchise
        FOREIGN KEY(franchise_id) 
        REFERENCES franchises(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    branch_id BIGINT NOT NULL,
    CONSTRAINT fk_branch
        FOREIGN KEY(branch_id) 
        REFERENCES branches(id)
        ON DELETE CASCADE
);
