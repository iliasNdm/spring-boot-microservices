CREATE TABLE IF NOT EXISTS customer_order
(
    id             BIGSERIAL PRIMARY KEY,
    reference      VARCHAR(255) NOT NULL,
    total_amount   NUMERIC(19, 2) NOT NULL,
    payment_method VARCHAR(50)  NOT NULL,
    customer_id    VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP    NOT NULL,
    updated_at     TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_line
(
    id          BIGSERIAL PRIMARY KEY,
    order_id    BIGINT REFERENCES customer_order(id),
    product_id  BIGINT NOT NULL,
    quantity    DOUBLE PRECISION NOT NULL
);