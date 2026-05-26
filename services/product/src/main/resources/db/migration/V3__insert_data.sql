-- Insert categories
INSERT INTO category (name, description) VALUES
                                             ('Electronics', 'Electronic devices and accessories'),
                                             ('Computers', 'Laptops, desktops and peripherals'),
                                             ('Smartphones', 'Mobile phones and accessories'),
                                             ('Audio', 'Headphones, speakers and sound equipment'),
                                             ('Gaming', 'Gaming consoles, games and accessories');

-- Insert products
INSERT INTO product (name, description, available_quantity, price, category_id) VALUES
                                                                                    ('MacBook Pro 14"', 'Apple MacBook Pro with M3 chip, 16GB RAM, 512GB SSD', 15, 1999.99, 2),
                                                                                    ('Dell XPS 15', 'Dell XPS 15 with Intel i9, 32GB RAM, 1TB SSD', 10, 1799.99, 2),
                                                                                    ('Mechanical Keyboard', 'RGB mechanical keyboard with Cherry MX switches', 50, 129.99, 2),
                                                                                    ('Logitech MX Master 3', 'Advanced wireless mouse for productivity', 35, 99.99, 2),

                                                                                    ('iPhone 15 Pro', 'Apple iPhone 15 Pro 256GB Titanium', 25, 1199.99, 3),
                                                                                    ('Samsung Galaxy S24', 'Samsung Galaxy S24 Ultra 512GB', 20, 1099.99, 3),
                                                                                    ('Google Pixel 8', 'Google Pixel 8 Pro 128GB', 18, 899.99, 3),
                                                                                    ('OnePlus 12', 'OnePlus 12 256GB Silky Black', 12, 799.99, 3),

                                                                                    ('Sony WH-1000XM5', 'Industry-leading noise cancelling headphones', 30, 349.99, 4),
                                                                                    ('AirPods Pro 2', 'Apple AirPods Pro 2nd generation with USB-C', 45, 249.99, 4),
                                                                                    ('Bose QuietComfort 45', 'Wireless noise cancelling headphones', 22, 299.99, 4),
                                                                                    ('JBL Charge 5', 'Portable waterproof Bluetooth speaker', 40, 179.99, 4),

                                                                                    ('PlayStation 5', 'Sony PlayStation 5 Digital Edition', 8, 449.99, 5),
                                                                                    ('Xbox Series X', 'Microsoft Xbox Series X 1TB', 6, 499.99, 5),
                                                                                    ('Nintendo Switch OLED', 'Nintendo Switch OLED Model White', 20, 349.99, 5),
                                                                                    ('Steam Deck 512GB', 'Valve Steam Deck 512GB NVMe SSD', 14, 649.99, 5),

                                                                                    ('4K Smart TV 55"', 'Samsung 55 inch 4K QLED Smart TV', 10, 899.99, 1),
                                                                                    ('iPad Pro 12.9"', 'Apple iPad Pro 12.9 inch M2 chip 256GB', 18, 1099.99, 1),
                                                                                    ('GoPro Hero 12', 'GoPro Hero 12 Black Action Camera', 25, 399.99, 1),
                                                                                    ('Kindle Paperwhite', 'Amazon Kindle Paperwhite 16GB Waterproof', 60, 149.99, 1);