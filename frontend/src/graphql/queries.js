import { gql } from '@apollo/client/index.js';

export const GET_KEY_METRICS = gql`
  query GetKeyMetrics($storeId: Int, $startDate: String, $endDate: String) {
    getKeyMetrics(storeId: $storeId, startDate: $startDate, endDate: $endDate) {
      totalRevenue
      activeRentals
    }
  }
`;

export const GET_TOP_RENTED_FILMS = gql`
  query GetTopRentedFilms($storeId: Int, $startDate: String, $endDate: String) {
    getTopRentedFilms(storeId: $storeId, startDate: $startDate, endDate: $endDate) {
      filmId
      title
      rentalCount
    }
  }
`;

export const GET_REVENUE_BY_CATEGORY = gql`
  query GetRevenueByCategory($storeId: Int, $startDate: String, $endDate: String) {
    getRevenueByCategory(storeId: $storeId, startDate: $startDate, endDate: $endDate) {
      category
      revenue
      percentage
    }
  }
`;

export const GET_TOP_CUSTOMERS = gql`
  query GetTopCustomers($storeId: Int, $startDate: String, $endDate: String) {
    getTopCustomers(storeId: $storeId, startDate: $startDate, endDate: $endDate) {
      customerId
      fullName
      totalRentals
      totalSpent
    }
  }
`;

export const GET_RECENT_TRANSACTIONS = gql`
  query GetRecentTransactions($storeId: Int, $startDate: String, $endDate: String, $limit: Int) {
    getRecentTransactions(storeId: $storeId, startDate: $startDate, endDate: $endDate, limit: $limit) {
      paymentId
      customerName
      filmTitle
      amount
      paymentDate
    }
  }
`;
