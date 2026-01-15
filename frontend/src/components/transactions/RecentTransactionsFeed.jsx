import React from 'react';
import { useFilteredQuery } from '../../hooks/useFilteredQuery';
import { GET_RECENT_TRANSACTIONS } from '../../graphql/queries';
import { formatCurrency, formatRelativeTime, getInitials } from '../../utils/formatters';
import { CHART_COLORS } from '../../utils/constants';

const RecentTransactionsFeed = () => {
  const { loading, error, data, refetch } = useFilteredQuery(GET_RECENT_TRANSACTIONS, 'getRecentTransactions');

  const transactions = data || [];

  if (error) {
    return (
      <div className="bg-white rounded-lg shadow-md p-6">
        <div className="text-red-800">
          <h3 className="text-lg font-semibold mb-2">Recent Transactions</h3>
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
          <div className="space-y-3">
            {[...Array(10)].map((_, i) => (
              <div key={i} className="flex items-center gap-3">
                <div className="w-10 h-10 bg-gray-200 rounded-full"></div>
                <div className="flex-1">
                  <div className="h-4 bg-gray-200 rounded w-3/4 mb-2"></div>
                  <div className="h-3 bg-gray-200 rounded w-1/2"></div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    );
  }

  if (!transactions.length) {
    return (
      <div className="bg-white rounded-lg shadow-md p-6">
        <h3 className="text-lg font-semibold mb-2">Recent Transactions</h3>
        <p className="text-gray-500">No transactions found for the selected filters.</p>
      </div>
    );
  }

  const getAvatarColor = (name) => {
    const colors = CHART_COLORS;
    const index = name.charCodeAt(0) % colors.length;
    return colors[index];
  };

  return (
    <div className="bg-white rounded-lg shadow-md p-6">
      <h3 className="text-lg font-semibold mb-4">Recent Transactions</h3>
      <div className="max-h-96 overflow-y-auto">
        <div className="space-y-3">
          {transactions.map((transaction) => (
            <div 
              key={transaction.paymentId}
              className="flex items-start gap-3 p-3 border-b border-gray-100 last:border-b-0 hover:bg-gray-50 transition-colors"
            >
              {/* Avatar */}
              <div 
                className="w-10 h-10 rounded-full flex items-center justify-center text-white font-semibold text-sm flex-shrink-0"
                style={{ backgroundColor: getAvatarColor(transaction.customerName) }}
              >
                {getInitials(transaction.customerName)}
              </div>
              
              {/* Transaction Details */}
              <div className="flex-1 min-w-0">
                <p className="text-gray-900 font-medium text-sm">
                  <span className="font-semibold">{transaction.customerName}</span>
                  <span className="text-gray-600 mx-1">rented</span>
                  <span className="font-semibold">"{transaction.filmTitle}"</span>
                  <span className="text-gray-600 mx-1">for</span>
                  <span className="font-semibold text-green-600">{formatCurrency(transaction.amount)}</span>
                </p>
                <p className="text-xs text-gray-500 mt-1">
                  {formatRelativeTime(transaction.paymentDate)}
                </p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default RecentTransactionsFeed;
