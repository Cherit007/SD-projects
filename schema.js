// createTablesRoute.js
const express = require("express");
const router = express.Router();
const connection = require("./db");

// SQL queries to create each table
const createOrdersTable = `
  CREATE TABLE IF NOT EXISTS orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'pending',
    total_amount DECIMAL(10, 2) NOT NULL
  );
`;

const createCustomersTable = `
  CREATE TABLE IF NOT EXISTS customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );
`;

const createInventoryTable = `
  CREATE TABLE IF NOT EXISTS inventory (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    stock_quantity INT NOT NULL DEFAULT 0,
    price DECIMAL(10, 2) NOT NULL
  );
`;

const createPaymentsTable = `
  CREATE TABLE IF NOT EXISTS payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    payment_status VARCHAR(50) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DECIMAL(10, 2) NOT NULL
  );
`;

// Route to create tables
router.get("/create-table", (req, res) => {
  connection.query(createOrdersTable, (err, results) => {
    if (err) {
      console.error("Error creating orders table:", err.stack);
      return res.status(500).send("Error creating orders table");
    }

    connection.query(createCustomersTable, (err, results) => {
      if (err) {
        console.error("Error creating customers table:", err.stack);
        return res.status(500).send("Error creating customers table");
      }

      connection.query(createInventoryTable, (err, results) => {
        if (err) {
          console.error("Error creating inventory table:", err.stack);
          return res.status(500).send("Error creating inventory table");
        }

        connection.query(createPaymentsTable, (err, results) => {
          if (err) {
            console.error("Error creating payments table:", err.stack);
            return res.status(500).send("Error creating payments table");
          }

          res.status(200).send("Tables created successfully");
        });
      });
    });
  });
});


module.exports = router;
