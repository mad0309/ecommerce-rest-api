-- Users (password: 'password123' encoded with BCrypt)
INSERT INTO users (first_name, last_name, email, password, role, created_at) VALUES
('Admin', 'User', 'admin@ecommerce.com', '$2b$10$XmGEWPGCrmILFZqHmF0vH.fGS8uTN4E2rx2mWWlJe9COpGCYyEjt6', 'ADMIN', CURRENT_TIMESTAMP),
('John', 'Doe', 'john@example.com', '$2b$10$XmGEWPGCrmILFZqHmF0vH.fGS8uTN4E2rx2mWWlJe9COpGCYyEjt6', 'USER', CURRENT_TIMESTAMP),
('Jane', 'Smith', 'jane@example.com', '$2b$10$XmGEWPGCrmILFZqHmF0vH.fGS8uTN4E2rx2mWWlJe9COpGCYyEjt6', 'USER', CURRENT_TIMESTAMP);

-- Products
INSERT INTO products (name, description, price, stock, category, image_url, created_at) VALUES
('MacBook Pro 16"', 'Apple MacBook Pro with M3 chip, 16GB RAM, 512GB SSD', 2499.99, 50, 'ELECTRONICS', 'https://example.com/macbook.jpg', CURRENT_TIMESTAMP),
('iPhone 15 Pro', 'Apple iPhone 15 Pro 256GB', 1199.99, 100, 'ELECTRONICS', 'https://example.com/iphone.jpg', CURRENT_TIMESTAMP),
('Nike Air Max 90', 'Classic Nike Air Max 90 sneakers', 129.99, 200, 'CLOTHING', 'https://example.com/nike.jpg', CURRENT_TIMESTAMP),
('Clean Code', 'A Handbook of Agile Software Craftsmanship by Robert C. Martin', 39.99, 500, 'BOOKS', 'https://example.com/cleancode.jpg', CURRENT_TIMESTAMP),
('Standing Desk', 'Adjustable height standing desk 60x30 inches', 349.99, 30, 'HOME', 'https://example.com/desk.jpg', CURRENT_TIMESTAMP),
('Yoga Mat', 'Premium non-slip yoga mat 6mm thick', 29.99, 300, 'SPORTS', 'https://example.com/yogamat.jpg', CURRENT_TIMESTAMP),
('Organic Coffee Beans', 'Premium organic coffee beans 1kg', 24.99, 150, 'FOOD', 'https://example.com/coffee.jpg', CURRENT_TIMESTAMP),
('Wireless Mouse', 'Logitech MX Master 3S wireless mouse', 99.99, 80, 'ELECTRONICS', 'https://example.com/mouse.jpg', CURRENT_TIMESTAMP);