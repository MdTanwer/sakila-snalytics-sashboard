import React, { useState, useMemo } from 'react';
import { useFilteredQuery } from '../../hooks/useFilteredQuery';
import { GET_TOP_CUSTOMERS } from '../../graphql/queries';
import { formatCurrency } from '../../utils/formatters';
import { ChevronUp, ChevronDown } from 'lucide-react';

const TopCustomersTable = () => {
  const { loading, error, data, refetch } = useFilteredQuery(GET_TOP_CUSTOMERS, 'getTopCustomers');
  const [sortConfig, setSortConfig] = useState({ key: 'totalSpent', direction: 'desc' });

  const customers = data || [];

  const handleSort = (key) => {
    setSortConfig(prevConfig => ({
      key,
      direction: prevConfig.key === key && prevConfig.direction === 'desc' ? 'asc' : 'desc'
    }));
  };

  const sortedCustomers = useMemo(() => {
    if (!customers.length) return [];

    return [...customers].sort((a, b) => {
      const aValue = a[sortConfig.key];
      const bValue = b[sortConfig.key];

      if (aValue < bValue) {
        return sortConfig.direction === 'asc' ? -1 : 1;
      }
      if (aValue > bValue) {
        return sortConfig.direction === 'asc' ? 1 : -1;
      }
      return 0;
    });
  }, [customers, sortConfig]);

  if (error) {
    return (
      <div className="bg-white rounded-lg shadow-md p-6">
        <div className="text-red-800">
          <h3 className="text-lg font-semibold mb-2">Top 10 Customers</h3>
          <p className="text-sm">{error.message}</p>
          <button
            onClick={() => refetch()}
            className="mt-3 px-4 py-2 bg-red-600 text-white rounded-md hover:bg-red-700 transition-colors"
          >
            Retry
          </button>
        </div>
      </div>
    );
  }

  if (loading) {
    return (
      <div className="bg-white rounded-lg shadow-md p-6">
        <div className="animate-pulse">
          <div className="h-6 bg-gray-200 rounded w-48 mb-4"></div>
          <div className="space-y-2">
            {[...Array(10)].map((_, i) => (
              <div key={i} className="h-10 bg-gray-200 rounded"></div>
            ))}
          </div>
        </div>
      </div>
    );
  }

  if (!customers.length) {
    return (
      <div className="bg-white rounded-lg shadow-md p-6">
        <h3 className="text-lg font-semibold mb-2">Top 10 Customers</h3>
        <p className="text-gray-500">No customer data found for the selected filters.</p>
      </div>
    );
  }

  const getSortIcon = (key) => {
    if (sortConfig.key !== key) {
      return <ChevronUp size={16} className="text-gray-400" />;
    }
    return sortConfig.direction === 'desc' 
      ? <ChevronDown size={16} className="text-blue-600" />
      : <ChevronUp size={16} className="text-blue-600" />;
  };

  return (
    <div className="bg-white rounded-lg shadow-md p-6">
      <h3 className="text-lg font-semibold mb-4">Top 10 Customers</h3>
      <div className="overflow-x-auto">
        <table className="w-full border-collapse">
          <thead>
            <tr className="border-b-2 border-gray-200">
              <th 
                className="text-right py-3 px-4 font-semibold text-gray-700 cursor-pointer hover:bg-gray-50"
                onClick={() => handleSort('customerId')}
              >
                <div className="flex items-center justify-end gap-1">
                  Customer ID
                  {getSortIcon('customerId')}
                </div>
              </th>
              <th className="text-left py-3 px-4 font-semibold text-gray-700">
                Full Name
              </th>
              <th 
                className="text-right py-3 px-4 font-semibold text-gray-700 cursor-pointer hover:bg-gray-50"
                onClick={() => handleSort('totalRentals')}
              >
                <div className="flex items-center justify-end gap-1">
                  Total Rentals
                  {getSortIcon('totalRentals')}
                </div>
              </th>
              <th 
                className="text-right py-3 px-4 font-semibold text-gray-700 cursor-pointer hover:bg-gray-50"
                onClick={() => handleSort('totalSpent')}
              >
                <div className="flex items-center justify-end gap-1">
                  Total Spent
                  {getSortIcon('totalSpent')}
                </div>
              </th>
            </tr>
          </thead>
          <tbody>
            {sortedCustomers.map((customer, index) => (
              <tr 
                key={customer.customerId}
                className={`border-b border-gray-100 hover:bg-gray-50 transition-colors ${
                  index % 2 === 0 ? 'bg-white' : 'bg-gray-50'
                } ${index === 0 ? 'font-bold' : ''}`}
              >
                <td className="text-right py-3 px-4 text-gray-900">
                  {customer.customerId}
                </td>
                <td className="text-left py-3 px-4 text-gray-900">
                  {customer.fullName}
                </td>
                <td className="text-right py-3 px-4 text-gray-900">
                  {customer.totalRentals.toLocaleString()}
                </td>
                <td className="text-right py-3 px-4 text-gray-900">
                  {formatCurrency(customer.totalSpent)}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default TopCustomersTable;
