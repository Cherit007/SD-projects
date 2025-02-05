const { gql } = require("apollo-server-express");

const typeDefs = gql`
  type Query {
    _empty: String
    getOrderByStatus(status: String!): [Order]
  }

  input OrderInput {
    customer_id: Int!
    total_amount: Float!
    status: String
  }

  type Order {
    order_id: Int!
    customer_id: Int!
    total_amount: Float!
    status: String!
  }

  type Mutation {
    addOrder(input: OrderInput!): Order
  }
`;

module.exports = typeDefs;
