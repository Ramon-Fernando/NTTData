INSERT INTO tb_order (moment, total_price) VALUES (NOW(), 250.75);
INSERT INTO tb_order (moment, total_price) VALUES (NOW(), 75.00);

INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (1, 1, 2, 50.25);
INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (1, 2, 1, 150.25);
INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (2, 3, 3, 25.00);