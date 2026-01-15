import React, { useState } from 'react';
import DatePicker from 'react-datepicker';
import { useFilters } from '../../context/FilterContext';
import 'react-datepicker/dist/react-datepicker.css';

const GlobalFilter = () => {
  const { filters, setStoreId, setDateRange, STORE_OPTIONS } = useFilters();
  const [localStartDate, setLocalStartDate] = useState(new Date(filters.startDate));
  const [localEndDate, setLocalEndDate] = useState(new Date(filters.endDate));

  const handleApply = () => {
    setDateRange(
      localStartDate.toISOString().split('T')[0],
      localEndDate.toISOString().split('T')[0]
    );
  };

  const handleReset = () => {
    const today = new Date();
    const thirtyDaysAgo = new Date(today.getTime() - (30 * 24 * 60 * 60 * 1000));
    
    setLocalStartDate(thirtyDaysAgo);
    setLocalEndDate(today);
    setStoreId(null);
    setDateRange(
      thirtyDaysAgo.toISOString().split('T')[0],
      today.toISOString().split('T')[0]
    );
  };

  const handleStoreChange = (e) => {
    const value = e.target.value === 'null' ? null : parseInt(e.target.value);
    setStoreId(value);
  };

  return (
    <div className="px-4 sm:px-6 lg:px-8 py-6">
      <div className="mx-auto">
        <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
          {/* Left: Dashboard Overview Title */}
          <div className="flex items-center">
            <h2 className="text-xl font-medium text-gray-600">
              Dashboard Overview
            </h2>
          </div>

          {/* Right: Filter Controls */}
          <div className="flex flex-wrap items-center gap-3">
            {/* Store Dropdown */}
            <div className="min-w-0">
              <select
                value={filters.storeId === null ? 'null' : filters.storeId}
                onChange={handleStoreChange}
                className="px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 bg-white text-sm"
              >
                {STORE_OPTIONS.map(option => (
                  <option key={option.value} value={option.value === null ? 'null' : option.value}>
                    {option.label}
                  </option>
                ))}
              </select>
            </div>

            {/* Date Range */}
            <div className="min-w-0">
              <div className="flex items-center gap-2">
                <span className="text-xs text-gray-500">Date Range</span>
                <span className="text-xs text-gray-500">From</span>
                <DatePicker
                  selected={localStartDate}
                  onChange={(date) => setLocalStartDate(date)}
                  selectsStart
                  startDate={localStartDate}
                  endDate={localEndDate}
                  placeholderText="Start Date"
                  className="px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 text-sm w-32"
                />
                <span className="text-xs text-gray-500">To</span>
                <DatePicker
                  selected={localEndDate}
                  onChange={(date) => setLocalEndDate(date)}
                  selectsEnd
                  startDate={localStartDate}
                  endDate={localEndDate}
                  minDate={localStartDate}
                  placeholderText="End Date"
                  className="px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 text-sm w-32"
                />
              </div>
            </div>

            {/* Apply Button */}
            <button
              onClick={handleApply}
              className="px-4 py-2 bg-blue-600 text-white text-sm font-medium rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-colors duration-200"
            >
              Apply
            </button>

            {/* Reset Button */}
            <button
              onClick={handleReset}
              className="px-4 py-2 bg-white text-gray-700 text-sm font-medium rounded-md border border-gray-300 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-colors duration-200"
            >
              Reset
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default GlobalFilter;
