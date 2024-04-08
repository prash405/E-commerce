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
