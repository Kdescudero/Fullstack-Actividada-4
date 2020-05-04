CREATE TABLE USERS(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    USERNAME VARCHAR NOT NULL,
    PASSWORD VARCHAR NOT NULL
);

CREATE TABLE PRODUCTS(
    ID                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    name               VARCHAR NOT NULL,
    description        VARCHAR NOT NULL,
    basePrice          DECIMAL NOT NULL,
    taxRate            DECIMAL NOT NULL,
    productStatus      VARCHAR NOT NULL,
    inventoryQuantity  INT NOT NULL
)