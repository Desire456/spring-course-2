CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE orders
(
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGSERIAL                 NOT NULL,
    order_number VARCHAR(32)               NOT NULL,
    amount       INTEGER     DEFAULT 0     NOT NULL CHECK (amount >= 0),
    currency     INTEGER     DEFAULT 810 CHECK (currency >= 0),
    return_url   VARCHAR(512)              NOT NULL,
    fail_url     VARCHAR(512),
    order_status VARCHAR(30) DEFAULT 'NEW' NOT NULL,
    CONSTRAINT fk_order_user_id
        FOREIGN KEY (user_id)
            REFERENCES users (id)
);