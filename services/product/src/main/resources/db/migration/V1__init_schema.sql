CREATE TABLE IF NOT EXISTS category
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS product
(
    id                  BIGSERIAL PRIMARY KEY,
    name                VARCHAR(255)     NOT NULL,
    description         VARCHAR(255),
    available_quantity  DOUBLE PRECISION NOT NULL,
    price               NUMERIC(38, 2)   NOT NULL,
    category_id         BIGINT REFERENCES category(id)
    );