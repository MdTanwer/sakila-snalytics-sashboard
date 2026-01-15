import React from 'react';
import { ApolloProvider } from '@apollo/client/react';
import { FilterProvider } from './context/FilterContext';
import client from './graphql/client';
import DashboardLayout from './components/layout/DashboardLayout';
import GlobalFilter from './components/filters/GlobalFilter';
import KPICards from './components/metrics/KPICards';
import TopRentedFilmsChart from './components/charts/TopRentedFilmsChart';
import RevenueDistributionChart from './components/charts/RevenueDistributionChart';
import TopCustomersTable from './components/tables/TopCustomersTable';
import RecentTransactionsFeed from './components/transactions/RecentTransactionsFeed';

function App() {
  return (
    <ApolloProvider client={client}>
      <FilterProvider>
        <DashboardLayout>
          {/* Global Filter - Sticky Header */}
          <GlobalFilter />
          <br />

          {/* KPI Cards - Row with 2 columns */}
          <KPICards />

          {/* Top Rented Films Chart - Full width */}
          {/* <TopRentedFilmsChart /> */}

          {/* Revenue Distribution and Top Customers - Side by side on desktop */}
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 mb-6">
            {/* <RevenueDistributionChart /> */}
            {/* <TopCustomersTable /> */}
          </div>

          {/* Recent Transactions Feed - Full width */}
          {/* <RecentTransactionsFeed /> */}
        </DashboardLayout>
      </FilterProvider>
    </ApolloProvider>
  );
}

export default App;
