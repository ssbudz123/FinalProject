DROP TABLE IF EXISTS models;
DROP TABLE IF EXISTS color;
DROP TABLE IF EXISTS price;
DROP TABLE IF EXISTS cpu;
DROP TABLE IF EXISTS storage;


CREATE TABLE orders (
order_pk int unsigned NOT NULL AUTO_INCREMENT,
customer_fk int unsigned NOT NULL,
color_fk int unsigned NOT NULL,
model_fk int unsigned NOT NULL,
price decimal(9, 2) NOT NULL,
PRIMARY KEY (order_pk),
FOREIGN KEY (color_fk) REFERENCES colors (color_pk) ON DELETE CASCADE,
FOREIGN KEY (model_fk) REFERENCES models (model_pk) ON DELETE CASCADE
);
  
  
  CREATE TABLE models (
  model_pk int unsigned NOT NULL AUTO_INCREMENT,
  model_id enum('IPHONE14PRO', 'IPHONE13', 'IPAD', 'IPADPRO') NOT NULL,
  color_id varchar(40) NOT NULL,
  base_price int NOT NULL,
  cpu_gen int NOT NULL,
  storage_avail decimal(9, 2) NOT NULL,
  PRIMARY KEY (model_pk),
  UNIQUE KEY (model_id)
);
  
  CREATE TABLE colors (
  color_pk int unsigned NOT NULL AUTO_INCREMENT,
  color_id varchar(30) NOT NULL,
  color varchar(60) NOT NULL,
  PRIMARY KEY (color_pk),
  UNIQUE KEY (color_id)
);
  
  
  
