const express = require("express");
const { ApolloServer, gql } = require("apollo-server-express");
const typeDefs = require("./graphql_schema");
const resolvers = require("./resolvers");
const createTablesRoute = require("./schema"); // Assuming this creates tables for your DB
const startConsumer = require("./kafka_consumer"); // Import the consumer logic

const app = express();
const PORT = 4000;

// Set up routes
app.use("/api", createTablesRoute); // Your database setup route

// Set up Apollo Server
const server = new ApolloServer({ typeDefs, resolvers });

async function startServer() {
  // Await for Apollo server to start
  await server.start();

  // Apply Apollo middleware after server starts
  server.applyMiddleware({ app });

  console.log("Producer connected to Kafka");

  await startConsumer(); // Start the consumer to listen for messages

  // Start the Express server
  app.listen(PORT, () => {
    console.log(`🚀 Server running on http://localhost:${PORT}/graphql`);
  });
}

startServer();
