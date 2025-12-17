CREATE TABLE IF NOT EXISTS users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255) UNIQUE,
  password VARCHAR(255),
  role VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS properties (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255),
  description CLOB,
  price DOUBLE,
  location VARCHAR(255),
  available BOOLEAN,
  owner_id BIGINT,
  version BIGINT,
  CONSTRAINT fk_owner FOREIGN KEY(owner_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS bookings (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  property_id BIGINT,
  status VARCHAR(50),
  created_at TIMESTAMP,
  CONSTRAINT fk_b_user FOREIGN KEY(user_id) REFERENCES users(id),
  CONSTRAINT fk_b_property FOREIGN KEY(property_id) REFERENCES properties(id)
);
