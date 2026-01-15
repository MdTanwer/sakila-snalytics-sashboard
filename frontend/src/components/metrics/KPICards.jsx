import React from 'react';
import { DollarSign, Package } from 'lucide-react';
import { useFilteredQuery } from '../../hooks/useFilteredQuery';
import { GET_KEY_METRICS } from '../../graphql/queries';
import { formatCurrency } from '../../utils/formatters';
import { COLORS } from '../../utils/constants';

const KPICards = () => {
  const { loading, error, data, refetch } = useFilteredQuery(GET_KEY_METRICS, 'getKeyMetrics');

  const metrics = data || { totalRevenue: 0, activeRentals: 0 };

  if (error) {
    return (
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-6">
        <div className="bg-red-50 border border-red-200 rounded-lg p-6">
          <div className="text-red-800">
            <p className="font-medium">Error loading metrics</p>
            <p className="text-sm mt-1">{error.message}</p>
            <button
              onClick={() => refetch()}
              className="mt-3 px-4 py-2 bg-red-600 text-white rounded-md hover:bg-red-700 transition-colors"
            >
              Retry
            </button>
          </div>
        </div>
      </div>
    );
  }

  if (loading) {
    return (
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-6">
        <div className="animate-pulse">
          <div className="bg-white rounded-lg shadow p-6">
            <div className="h-4 bg-gray-200 rounded w-1/4 mb-4"></div>
            <div className="h-8 bg-gray-200 rounded w-1/2"></div>
          </div>
        </div>
        <div className="animate-pulse">
          <div className="bg-white rounded-lg shadow p-6">
            <div className="h-4 bg-gray-200 rounded w-1/4 mb-4"></div>
            <div className="h-8 bg-gray-200 rounded w-1/2"></div>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-6">
      {/* Total Revenue Card */}
      <div className="bg-white rounded-lg shadow-md p-6 border border-gray-100">
        <div className="flex items-center justify-between">
          <div>
            <p className="text-sm font-medium text-gray-600 mb-1">Total Revenue</p>
            <p className="text-3xl font-bold text-gray-900">
              {formatCurrency(metrics.totalRevenue)}
            </p>
          </div>
          <div className="p-3 bg-green-100 rounded-full">
            <DollarSign size={24} className="text-green-600" />
          </div>
        </div>
      </div>

      {/* Active Rentals Card */}
      <div className="bg-white rounded-lg shadow-md p-6 border border-gray-100">
        <div className="flex items-center justify-between">
          <div>
            <p className="text-sm font-medium text-gray-600 mb-1">Active Rentals</p>
            <p className="text-3xl font-bold text-gray-900">
              {metrics.activeRentals.toLocaleString()}
            </p>
          </div>
          <div className="p-3 bg-blue-100 rounded-full">
            <Package size={24} className="text-blue-600" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default KPICards;
