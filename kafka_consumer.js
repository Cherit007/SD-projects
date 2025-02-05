const {
  producer,
  orderConsumer,
  completedOrderConsumer,
} = require("./kafka_service");
const pool = require("./db");

async function startConsumer() {
  await startCreateOrderConsumer();
  await startCompletedOrdersConsumer();
}

async function startCompletedOrdersConsumer() {
  await completedOrderConsumer.connect();
  await completedOrderConsumer.subscribe({
    topic: "completed-orders",
    fromBeginning: true,
  });

  orderConsumer.run({
    eachMessage: async ({ message }) => {
      const completedOrder = JSON.parse(message.value.toString());
      console.log("Processing Completed Order:", completedOrder);

      // Placeholder logic for processing completed orders (e.g., logging, further processing)
      console.log(
        `Order ${completedOrder.id} is marked as completed and processed.`
      );

      // You can add more business logic here based on completed order information
    },
  });
}

async function startCreateOrderConsumer() {
  await orderConsumer.connect();
  await orderConsumer.subscribe({ topic: "orders", fromBeginning: true });

  orderConsumer.run({
    eachMessage: async ({ message }) => {
      const completedOrder = JSON.parse(message.value.toString());
      console.log("Processing Completed Order:", completedOrder);

      // Placeholder logic for processing completed orders (e.g., logging)
      console.log(
        `Order ${completedOrder.order_id} is marked as completed and processed.`
      );

      // Update the order status to "completed" in MySQL
      try {
        const [result] = await pool.execute(
          "UPDATE orders SET status = ? WHERE order_id = ?",
          ["completed", completedOrder.order_id]
        );
        console.log(
          `Order ${completedOrder.order_id} status updated to completed in the database.`
        );
        console.log(
          `Order ${completedOrder.order_id} processed and completed.`
        );
        // Send the completed order to Kafka's "completed-orders" topic
        await producer.send({
          topic: "completed-orders",
          messages: [
            {
              value: JSON.stringify(completedOrder),
            },
          ],
        });
        console.log(
          `Sent completed order to Kafka: ${completedOrder.order_id}`
        );
      } catch (err) {
        console.error("Error updating order status in MySQL:", err);
      }
    },
  });
}

module.exports = startConsumer;
