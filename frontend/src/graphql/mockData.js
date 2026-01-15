export const MOCK_MODE = true;

export const mockQueries = {
  getKeyMetrics: {
    totalRevenue: 45283.50,
    activeRentals: 183
  },
  getTopRentedFilms: [
    { filmId: 1, title: "BUCKET BROTHERHOOD", rentalCount: 34 },
    { filmId: 2, title: "ROCKETEER MOTHER", rentalCount: 33 },
    { filmId: 3, title: "JUGGLER HARDLY", rentalCount: 32 },
    { filmId: 4, title: "SCALAWAG DUCK", rentalCount: 31 },
    { filmId: 5, title: "RIDER CADDYSHACK", rentalCount: 30 }
  ],
  getRevenueByCategory: [
    { category: "Action", revenue: 12500.00, percentage: 0.35 },
    { category: "Comedy", revenue: 9083.50, percentage: 0.25 },
    { category: "Drama", revenue: 7245.00, percentage: 0.20 },
    { category: "Horror", revenue: 4528.35, percentage: 0.12 },
    { category: "Sci-Fi", revenue: 2726.65, percentage: 0.08 }
  ],
  getTopCustomers: [
    { customerId: 148, fullName: "ELEANOR HUNT", totalRentals: 46, totalSpent: 2215.50 },
    { customerId: 144, fullName: "CLARA SHAW", totalRentals: 42, totalSpent: 1987.25 },
    { customerId: 130, fullName: "KAREN JACKSON", totalRentals: 40, totalSpent: 1856.75 },
    { customerId: 115, fullName: "TERRY GRISSOM", totalRentals: 38, totalSpent: 1724.00 },
    { customerId: 137, fullName: "CYNTHIA YOUNG", totalRentals: 36, totalSpent: 1598.50 },
    { customerId: 126, fullName: "TAMMY SANDERS", totalRentals: 35, totalSpent: 1487.25 },
    { customerId: 118, fullName: "MARION SNYDER", totalRentals: 34, totalSpent: 1376.00 },
    { customerId: 145, fullName: "GINA WILLIAMSON", totalRentals: 33, totalSpent: 1265.75 },
    { customerId: 133, fullName: "MICHELLE CLARK", totalRentals: 32, totalSpent: 1155.50 },
    { customerId: 121, fullName: "SANDRA MARTINEZ", totalRentals: 31, totalSpent: 1045.25 }
  ],
  getRecentTransactions: [
    { paymentId: 32098, customerName: "ELEANOR HUNT", filmTitle: "BUCKET BROTHERHOOD", amount: 4.99, paymentDate: "2006-02-15T10:30:00Z" },
    { paymentId: 32097, customerName: "CLARA SHAW", filmTitle: "ROCKETEER MOTHER", amount: 2.99, paymentDate: "2006-02-15T09:45:00Z" },
    { paymentId: 32096, customerName: "KAREN JACKSON", filmTitle: "JUGGLER HARDLY", amount: 4.99, paymentDate: "2006-02-15T08:20:00Z" },
    { paymentId: 32095, customerName: "TERRY GRISSOM", filmTitle: "SCALAWAG DUCK", amount: 3.99, paymentDate: "2006-02-15T07:15:00Z" },
    { paymentId: 32094, customerName: "CYNTHIA YOUNG", filmTitle: "RIDER CADDYSHACK", amount: 2.99, paymentDate: "2006-02-15T06:30:00Z" },
    { paymentId: 32093, customerName: "TAMMY SANDERS", filmTitle: "CHAMBER ITALIAN", amount: 4.99, paymentDate: "2006-02-14T18:45:00Z" },
    { paymentId: 32092, customerName: "MARION SNYDER", filmTitle: "APACHE DIVINE", amount: 3.99, paymentDate: "2006-02-14T17:20:00Z" },
    { paymentId: 32091, customerName: "GINA WILLIAMSON", filmTitle: "BETRAYED REAR", amount: 2.99, paymentDate: "2006-02-14T16:10:00Z" },
    { paymentId: 32090, customerName: "MICHELLE CLARK", filmTitle: "CATCH AMISTAD", amount: 4.99, paymentDate: "2006-02-14T15:30:00Z" },
    { paymentId: 32089, customerName: "SANDRA MARTINEZ", filmTitle: "CHINATOWN GLORY", amount: 3.99, paymentDate: "2006-02-14T14:45:00Z" }
  ]
};
