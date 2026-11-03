# 🛒 Retail & Supermarket Billing System (Java Swing + MySQL)

A desktop-based Point of Sale (POS) and Billing Management System built using **Java Swing** and **MySQL**. This application provides role-based interfaces for **Admin**, **Employees**, and **Customers** to handle inventory management, cash counter billing, receipt generation, and order history.

---

## 📸 Screenshots

| Splash Screen | Login Window |
|:---:|:---:|
| <img src="screenshots/Screenshot%202026-11-02%20234308.png" width="400" alt="Splash Screen" /> | <img src="screenshots/Screenshot%202026-11-02%20234332.png" width="400" alt="Login Window" /> |

| Add to Cart & POS Billing | View All Employees |
|:---:|:---:|
| <img src="screenshots/Screenshot%202026-11-02%20154352.png" width="400" alt="Add Product to Cart" /> | <img src="screenshots/Screenshot%202026-11-02%20234619.png" width="400" alt="View All Employees" /> |

---

## 📌 Features

### 👑 Admin Module
- **Dashboard & Analytics:** Centralized interface to manage store operations.
- **Employee Management:** Add new employees, update details, and view/delete employee records.
- **Product & Inventory Management:** 
  - Add new products with item code, name, category, price, quantity, and product image.
  - Edit or update existing product specifications and stock levels.
  - Delete discontinued items.
- **Security:** Admin password change and credential management.

### 💼 Employee / Cashier Module
- **Point of Sale (POS) Billing:**
  - Fast search and selection of inventory items.
  - Dynamic cart management (add, edit quantity, remove items).
  - Real-time subtotal, discount, and grand total calculations.
- **Checkout & Payment:**
  - Multiple payment options (Cash, Card, UPI, etc.).
  - Receipt / Bill generation for customer transactions.
- **Profile & Account:** View cashier profile details and change password.

### 👤 Customer Module
- **Customer Portal:** Registration and Login functionality for repeat customers.
- **Order Tracking:** View purchase history and detailed receipts of past orders.

### 🛡️ Input Validation & UX
- Custom validation for email formats, phone numbers, and required fields.
- Attractive user interface with custom icons, banner images, and splash loading screen.

---

## 🛠️ Tech Stack & Prerequisites

- **Language:** Java (JDK 8 or higher)
- **GUI Framework:** Java Swing / AWT (NetBeans GUI Builder / AbsoluteLayout)
- **Database:** MySQL Server 8.0+
- **Database Connectivity:** JDBC (`com.mysql.cj.jdbc.Driver`)
- **IDE:** NetBeans IDE

---

## 🗄️ Database Setup

1. Open your MySQL client (MySQL Workbench, phpMyAdmin, or Command Line).
2. Create a database named `billing_software`:

```sql
CREATE DATABASE IF NOT EXISTS billing_software;
USE billing_software;
```

3. Create the required tables:

```sql
-- 1. Users/Employees/Admin Table
CREATE TABLE IF NOT EXISTS register (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    gender VARCHAR(10),
    module VARCHAR(20) -- e.g., 'admin', 'emp', 'cust'
);

-- Insert a default Admin user (adjust credentials as needed)
INSERT INTO register (name, email, password, phone, gender, module)
VALUES ('Admin', 'admin@gmail.com', 'admin123', '9876543210', 'Male', 'admin');

-- 2. Items / Inventory Table
CREATE TABLE IF NOT EXISTS items (
    item_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    description VARCHAR(1000),
    img MEDIUMBLOB
);

-- 3. Orders Table
CREATE TABLE IF NOT EXISTS orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    products TEXT NOT NULL,
    price FLOAT NOT NULL,
    user_id INT,
    email VARCHAR(100),
    phone VARCHAR(10),
    gender VARCHAR(1),
    name VARCHAR(100),
    payment_method VARCHAR(50),
    admin_id INT,
    datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES register(id) ON DELETE SET NULL,
    FOREIGN KEY (admin_id) REFERENCES register(id) ON DELETE SET NULL
);
```

4. Configure database credentials in [`src/DbCon/DbConnection.java`](src/DbCon/DbConnection.java):
```java
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_software", "YOUR_MYSQL_USERNAME", "YOUR_MYSQL_PASSWORD");
```

---

## 🚀 How to Run the Project

### Option A: Quick Run (Pre-built Release)
1. Go to the [Releases](https://github.com/v1v3k-7/BillingSoftware/releases) section of this repository.
2. Download the latest `BillingSoftware-v1.0.zip` file.
3. Extract the ZIP file onto your computer.
4. Ensure you have **Java (JRE/JDK 8+)** installed and your **MySQL database** configured as described above.
5. Double-click `BillingSoftware.jar` (or run in terminal: `java -jar BillingSoftware.jar`).

### Option B: Using NetBeans IDE
1. Open **NetBeans IDE**.
2. Go to **File -> Open Project** and browse to this directory.
3. Ensure MySQL Connector/J (`mysql-connector-j-x.x.x.jar`) is added to the project's **Libraries**.
4. Set the main class to `billingsoftware.BillingSoftware`.
5. Click **Run Project (F6)**.

### Option C: Using Command Line & Ant
```bash
# Build the project
ant compile

# Run the project
ant run
```

---

## 📂 Project Structure

```text
BillingSoftware/
├── src/
│   ├── admin/                # Admin views (Add/Edit Item, Manage Employees)
│   ├── billingsoftware/      # App entry point, Splash Screen, Login
│   ├── customer/             # Customer Signup & Portal
│   ├── DbCon/                # Database Connection and SQL queries
│   ├── employee/             # Cashier billing, cart, invoice generation
│   ├── images/               # UI icons and backgrounds
│   └── validations/          # Form and input validation helpers
├── screenshots/              # UI screenshots for documentation
└── README.md
```

---

## 🤝 Contributing
Contributions, issues, and feature requests are welcome! Feel free to check the issues page or submit a pull request.

## 📄 License
This project is open-source and created for educational purposes.
