const mysql = require("mysql2");

const connection = mysql.createConnection({
  host: "localhost",
  user: "user",
  password: "userpassword",
  database: "order_management",
  port: 3307,
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0,
});

connection.connect((err) => {
  if (err) throw err;
  console.log("Connected to MySQL database");
});

module.exports = connection.promise();
