INSERT INTO users(username, password)
VALUES ('user', 'pass');

INSERT INTO orders(user_id, order_number, amount, return_url, fail_url)
VALUES (1, 'order1', 15, 'https://github.com', 'https://github.com/Desire456');