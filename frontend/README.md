# Sakila Analytics Dashboard Frontend

A React-based Single Page Application for visualizing DVD rental store analytics. This dashboard connects to a GraphQL API and displays 5 key business metrics with global filtering capabilities.

## 🚀 Tech Stack

- **Framework**: React 18+ with Vite
- **GraphQL Client**: Apollo Client (@apollo/client)
- **Charting Library**: Recharts
- **Styling**: Tailwind CSS
- **Date Picker**: react-datepicker
- **Icons**: lucide-react
- **State Management**: React Context API
- **Date Utilities**: date-fns

## 📋 Features

### 📊 Key Metrics
- **Total Revenue**: Displays total rental revenue with currency formatting
- **Active Rentals**: Shows current number of active rentals
- **Top 5 Rented Films**: Horizontal bar chart showing most rented films
- **Revenue Distribution**: Pie chart showing revenue by film category
- **Top 10 Customers**: Sortable table of highest-spending customers
- **Recent Transactions**: Live feed of recent rental payments

### 🎛️ Global Filtering
- **Store Filter**: Dropdown to filter by "All Stores", "Store 1", or "Store 2"
- **Date Range Picker**: Select start and end dates for filtering data
- **Default Date Range**: 2005-01-01 to 2006-12-31 (matches Sakila database data)

### 📱 Responsive Design
- Desktop: Multi-column layout with side-by-side components
- Tablet: Adaptive layout for medium screens
- Mobile: Stacked vertical layout for small screens

## 🛠️ Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd sakila-dashboard-frontend
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Setup environment variables**
   ```bash
   cp .env.example .env
   ```
   Edit `.env` and set your GraphQL endpoint:
   ```
   VITE_GRAPHQL_ENDPOINT=http://localhost:8080/graphql
   ```

4. **Start development server**
   ```bash
   npm run dev
   ```

5. **Open your browser**
   Navigate to `http://localhost:5173`

## 📁 Project Structure

```
sakila-dashboard-frontend/
├── public/
├── src/
│   ├── components/
│   │   ├── layout/
│   │   │   ├── Header.jsx                    # App header with title
│   │   │   └── DashboardLayout.jsx           # Main layout wrapper
│   │   ├── filters/
│   │   │   └── GlobalFilter.jsx              # Store dropdown + Date range picker
│   │   ├── metrics/
│   │   │   └── KPICards.jsx                  # Total Revenue + Active Rentals cards
│   │   ├── charts/
│   │   │   ├── TopRentedFilmsChart.jsx       # Horizontal bar chart
│   │   │   └── RevenueDistributionChart.jsx  # Pie chart
│   │   ├── tables/
│   │   │   └── TopCustomersTable.jsx         # Sortable data table
│   │   └── transactions/
│   │       └── RecentTransactionsFeed.jsx    # Vertical list of recent payments
│   ├── context/
│   │   └── FilterContext.jsx                 # Global filter state management
│   ├── graphql/
│   │   ├── client.js                         # Apollo Client setup
│   │   ├── queries.js                        # All GraphQL queries
│   │   └── mockData.js                       # Mock data for development
│   ├── hooks/
│   │   └── useFilteredQuery.js               # Custom hook for filtered queries
│   ├── utils/
│   │   ├── formatters.js                     # Currency, date formatting helpers
│   │   └── constants.js                      # Color schemes, default values
│   ├── App.jsx                               # Main app component
│   ├── main.jsx                              # Entry point
│   └── index.css                             # Tailwind imports
├── .env.example                              # GraphQL endpoint example
├── package.json
├── vite.config.js
├── tailwind.config.js
└── README.md
```

## 🎭 Mock Mode

The dashboard includes a mock data mode for development when the backend is not available:

**To enable/disable mock mode:**
1. Open `src/graphql/mockData.js`
2. Change `export const MOCK_MODE = true;` to `false` to use real API
3. Change to `true` to use mock data

**Mock data includes:**
- Key metrics (revenue, active rentals)
- Top 5 rented films with rental counts
- Revenue distribution by category
- Top 10 customers with spending data
- Recent transactions with timestamps

## 🎨 Components Overview

### GlobalFilter Component
- Sticky header with store and date filters
- Debounced date picker (500ms delay)
- Responsive layout (horizontal on desktop, stacked on mobile)

### KPICards Component
- Two cards displaying total revenue and active rentals
- Loading skeletons and error handling
- Color-coded icons (green for revenue, blue for rentals)

### Charts
- **TopRentedFilmsChart**: Horizontal bar chart with gradient fill
- **RevenueDistributionChart**: Interactive pie chart with legend
- Both charts include tooltips and responsive design

### TopCustomersTable
- Sortable columns (Customer ID, Total Rentals, Total Spent)
- Alternating row colors and hover effects
- Top customer highlighted in bold

### RecentTransactionsFeed
- Avatar with customer initials
- Relative timestamps for recent transactions
- Scrollable list with max height

## 🔧 Development

### Available Scripts
- `npm run dev` - Start development server
- `npm run build` - Build for production
- `npm run preview` - Preview production build
- `npm run lint` - Run ESLint

### GraphQL Queries
The app uses the following GraphQL queries:
- `GET_KEY_METRICS` - Total revenue and active rentals
- `GET_TOP_RENTED_FILMS` - Top 5 films by rental count
- `GET_REVENUE_BY_CATEGORY` - Revenue distribution by category
- `GET_TOP_CUSTOMERS` - Top 10 customers by spending
- `GET_RECENT_TRANSACTIONS` - Recent payment transactions

### State Management
- Uses React Context API for global filter state
- FilterContext provides storeId, startDate, and endDate
- All components automatically update when filters change

## 🐛 Troubleshooting

### Common Issues

1. **Tailwind CSS not working**
   - Ensure `@tailwind` directives are in `index.css`
   - Check that `tailwind.config.js` content paths are correct

2. **GraphQL queries failing**
   - Verify the GraphQL endpoint in `.env` file
   - Check that the backend server is running
   - Enable mock mode if backend is unavailable

3. **Date picker issues**
   - Ensure `react-datepicker` CSS is imported
   - Check that date-fns is installed for formatting

4. **Charts not rendering**
   - Verify Recharts is installed
   - Check that data structure matches expected format
   - Ensure container has proper height/width

## 🚀 Production Deployment

1. **Build the application**
   ```bash
   npm run build
   ```

2. **Preview the build**
   ```bash
   npm run preview
   ```

3. **Deploy to your preferred platform**
   - The build output is in the `dist/` folder
   - Compatible with Vercel, Netlify, and other static hosting platforms

## 🔮 Known Limitations

- Backend GraphQL API is not implemented (mock mode available)
- Real-time updates are not supported
- No user authentication system
- Limited to Sakila database schema

## 🎯 Future Enhancements

- [ ] Real-time data updates with GraphQL subscriptions
- [ ] User authentication and role-based access
- [ ] Export functionality for charts and tables
- [ ] Advanced filtering options
- [ ] Dark mode support
- [ ] Mobile app version
- [ ] Data drill-down capabilities
- [ ] Custom date range presets
- [ ] Performance analytics and monitoring

## 📄 License

This project is licensed under the MIT License.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📞 Support

For questions or support, please open an issue in the repository.
