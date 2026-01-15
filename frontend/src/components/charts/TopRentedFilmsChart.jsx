import React from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';
import { useFilteredQuery } from '../../hooks/useFilteredQuery';
import { GET_TOP_RENTED_FILMS } from '../../graphql/queries';
import { CHART_COLORS } from '../../utils/constants';

const TopRentedFilmsChart = () => {
  const { loading, error, data, refetch } = useFilteredQuery(GET_TOP_RENTED_FILMS, 'getTopRentedFilms');

  const films = data || [];

  if (error) {
    return (
      <div className="bg-white rounded-lg shadow-md p-6 mb-6">
        <div className="text-red-800">
          <h3 className="text-lg font-semibold mb-2">Top 5 Rented Films</h3>
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
      <div className="bg-white rounded-lg shadow-md p-6 mb-6">
        <div className="animate-pulse">
          <div className="h-6 bg-gray-200 rounded w-48 mb-4"></div>
          <div className="h-96 bg-gray-200 rounded"></div>
        </div>
      </div>
    );
  }

  if (!films.length) {
    return (
      <div className="bg-white rounded-lg shadow-md p-6 mb-6">
        <h3 className="text-lg font-semibold mb-2">Top 5 Rented Films</h3>
        <p className="text-gray-500">No rental data found for the selected filters.</p>
      </div>
    );
  }

  const CustomTooltip = ({ active, payload }) => {
    if (active && payload && payload.length) {
      return (
        <div className="bg-white p-3 border border-gray-200 rounded-md shadow-lg">
          <p className="font-medium">{`Film: ${payload[0].payload.title}`}</p>
          <p className="text-sm text-gray-600">{`Rentals: ${payload[0].value}`}</p>
        </div>
      );
    }
    return null;
  };

  return (
    <div className="bg-white rounded-lg shadow-md p-6 mb-6">
      <h3 className="text-lg font-semibold mb-4">Top 5 Rented Films</h3>
      <div className="h-96">
        <ResponsiveContainer width="100%" height="100%">
          <BarChart
            data={films}
            layout="horizontal"
            margin={{ top: 5, right: 30, left: 20, bottom: 5 }}
          >
            <CartesianGrid strokeDasharray="3 3" stroke="#f0f0f0" />
            <XAxis 
              type="number" 
              dataKey="rentalCount"
              tick={{ fontSize: 12 }}
            />
            <YAxis 
              type="category" 
              dataKey="title"
              width={150}
              tick={{ fontSize: 12 }}
            />
            <Tooltip content={<CustomTooltip />} />
            <Bar 
              dataKey="rentalCount" 
              fill="url(#colorGradient)"
              radius={[0, 4, 4, 0]}
            />
            <defs>
              <linearGradient id="colorGradient" x1="0" y1="0" x2="1" y2="0">
                <stop offset="0%" stopColor={CHART_COLORS[0]} />
                <stop offset="100%" stopColor={CHART_COLORS[4]} />
              </linearGradient>
            </defs>
          </BarChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
};

export default TopRentedFilmsChart;
