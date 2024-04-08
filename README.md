# E-Commerce Database

This repository contains SQL scripts for creating and managing an e-commerce database.

## Files

- `schema.sql`: Contains SQL commands to create tables for Users, Products, and Cart.
- `README.md`: This file you're currently reading.
- `LICENSE`: License information for the project.

## Usage

1. Clone the repository:

    ```bash
    git clone <repository-url>
    ```

2. Execute the `schema.sql` script in your SQL database management tool to create the database and tables.

3. Start using the database for your e-commerce application.

## Database Structure

### Users Table

- UserID (Primary Key)
- FirstName
- LastName
- Username (Unique)
- Password
- Email (Unique)
- MobileNumber
- City

### Products Table

- ProductID (Primary Key)
- Name
- Description
- Price
- Quantity

### Cart Table

- CartID (Primary Key)
- UserID (Foreign Key referencing Users.UserID)
- ProductID (Foreign Key referencing Products.ProductID)
- Quantity
- Price

## License

This project is licensed under the [PrashDev]

CREATE DATABASE e_commerce;
create table user(
	UserID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Username VARCHAR(255) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    MobileNumber VARCHAR(15) NOT NULL,
    City VARCHAR(255) NOT NULL
);

CREATE TABLE Products (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Description VARCHAR(255) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Quantity INT NOT NULL
);

create Table cart(
id int auto_increment primary key,
Name VARCHAR(255) NOT NULL,
ProductID int not null,
Quantity INT NOT NULL,
Price DECIMAL(10, 2) NOT NULL
);
