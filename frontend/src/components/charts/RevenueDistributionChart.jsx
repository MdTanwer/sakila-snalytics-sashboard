import React from 'react';
import { PieChart, Pie, Cell, ResponsiveContainer, Tooltip, Legend } from 'recharts';
import { useFilteredQuery } from '../../hooks/useFilteredQuery';
import { GET_REVENUE_BY_CATEGORY } from '../../graphql/queries';
import { CHART_COLORS } from '../../utils/constants';
import { formatCurrency, formatPercentage } from '../../utils/formatters';

const RevenueDistributionChart = () => {
  const { loading, error, data, refetch } = useFilteredQuery(GET_REVENUE_BY_CATEGORY, 'getRevenueByCategory');

  const categories = data || [];

  if (error) {
    return (
      <div className="bg-white rounded-lg shadow-md p-6">
        <div className="text-red-800">
          <h3 className="text-lg font-semibold mb-2">Revenue Distribution by Category</h3>
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
          <div className="h-6 bg-gray-200 rounded w-64 mb-4"></div>
          <div className="h-96 bg-gray-200 rounded"></div>
        </div>
      </div>
    );
  }

  if (!categories.length) {
    return (
      <div className="bg-white rounded-lg shadow-md p-6">
        <h3 className="text-lg font-semibold mb-2">Revenue Distribution by Category</h3>
        <p className="text-gray-500">No revenue data found for the selected filters.</p>
      </div>
    );
  }

  const CustomTooltip = ({ active, payload }) => {
    if (active && payload && payload.length) {
      return (
        <div className="bg-white p-3 border border-gray-200 rounded-md shadow-lg">
          <p className="font-medium">{`Category: ${payload[0].name}`}</p>
          <p className="text-sm text-gray-600">{`Revenue: ${formatCurrency(payload[0].payload.revenue)}`}</p>
          <p className="text-sm text-gray-600">{`Percentage: ${formatPercentage(payload[0].payload.percentage)}`}</p>
        </div>
      );
    }
    return null;
  };

  const CustomLabel = ({ cx, cy, midAngle, innerRadius, outerRadius, percent }) => {
    const RADIAN = Math.PI / 180;
    const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
    const x = cx + radius * Math.cos(-midAngle * RADIAN);
    const y = cy + radius * Math.sin(-midAngle * RADIAN);

    if (percent < 0.05) return null; // Don't show label for small slices

    return (
      <text 
        x={x} 
        y={y} 
        fill="white" 
        textAnchor={x > cx ? 'start' : 'end'} 
        dominantBaseline="central"
        fontSize={12}
        fontWeight="bold"
      >
        {`${(percent * 100).toFixed(0)}%`}
      </text>
    );
  };

  return (
    <div className="bg-white rounded-lg shadow-md p-6">
      <h3 className="text-lg font-semibold mb-4">Revenue Distribution by Category</h3>
      <div className="h-96">
        <ResponsiveContainer width="100%" height="100%">
          <PieChart>
            <Pie
              data={categories}
              cx="50%"
              cy="50%"
              labelLine={false}
              label={CustomLabel}
              outerRadius={120}
              fill="#8884d8"
              dataKey="revenue"
              animationBegin={0}
              animationDuration={800}
            >
              {categories.map((entry, index) => (
                <Cell key={`cell-${index}`} fill={CHART_COLORS[index % CHART_COLORS.length]} />
              ))}
            </Pie>
            <Tooltip content={<CustomTooltip />} />
            <Legend 
              verticalAlign="middle" 
              align="right" 
              layout="vertical"
              formatter={(value, entry) => `${value} (${formatPercentage(entry.payload.percentage)})`}
            />
          </PieChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
};

export default RevenueDistributionChart;
