const pool = require("./db"); // Import the pool for MySQL connection
const { producer } = require("./kafka_service");

const resolvers = {
  Query: {
    getOrderByStatus: async (_, { status }) => {
      try {
        const [rows] = await pool.execute(
          "SELECT * FROM orders WHERE status = ?",
          [status]
        );
        return rows;
      } catch (err) {
        console.error("Error fetching orders:", err);
        throw new Error("Error fetching orders");
      }
    },
  },
  Mutation: {
    addOrder: async (_, { input }) => {
      const { customer_id, total_amount, status } = input;

      try {
        // Insert order into MySQL using the pool
        const [result] = await pool.execute(
          "INSERT INTO orders (customer_id, total_amount, status) VALUES (?, ?, ?)",
          [customer_id, total_amount, status || "pending"] // Default status to "pending"
        );

        console.log("Order added:", result);

        // Create order object to send to Kafka
        const order = {
          order_id: result.insertId, // The MySQL auto-generated order_id
          customer_id,
          total_amount,
          status: status || "pending",
        };
        await producer.send({
          topic: "orders",
          messages: [
            {
              value: JSON.stringify(order), // Send the order as a JSON string
            },
          ],
        });

        console.log("Order sent to Kafka:", order);

        // Return the inserted order with its ID
        return order;
      } catch (err) {
        console.error("Error adding order:", err);
        throw new Error("Error adding order");
      }
    },
  },
};

module.exports = resolvers;
