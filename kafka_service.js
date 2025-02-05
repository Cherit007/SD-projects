const { Kafka } = require("kafkajs");

// Initialize Kafka client and producer/consumer
const kafka = new Kafka({
  clientId: "order-service",
  brokers: ["localhost:9092"], // Make sure this matches your Kafka configuration
});
console.log("Kafka Service is up");
const producer = kafka.producer();
producer.connect();
const orderConsumer = kafka.consumer({ groupId: "order-group" });
const completedOrderConsumer = kafka.consumer({
  groupId: "completed-order-group",
});
module.exports = { producer, orderConsumer, completedOrderConsumer };
