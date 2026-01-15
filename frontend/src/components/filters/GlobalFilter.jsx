import React, { useState, useEffect } from 'react';
import DatePicker from 'react-datepicker';
import { useFilters } from '../../context/FilterContext';
import 'react-datepicker/dist/react-datepicker.css';

const GlobalFilter = () => {
  const { filters, setStoreId, setDateRange, STORE_OPTIONS } = useFilters();
  const [localStartDate, setLocalStartDate] = useState(new Date(filters.startDate));
  const [localEndDate, setLocalEndDate] = useState(new Date(filters.endDate));

  useEffect(() => {
    const timeoutId = setTimeout(() => {
      setDateRange(
        localStartDate.toISOString().split('T')[0],
        localEndDate.toISOString().split('T')[0]
      );
    }, 500);

    return () => clearTimeout(timeoutId);
  }, [localStartDate, localEndDate, setDateRange]);

  const handleStoreChange = (e) => {
    const value = e.target.value === 'null' ? null : parseInt(e.target.value);
    setStoreId(value);
  };

  return (
    <div className="sticky top-0 z-10 bg-gray-50 border-b border-gray-200 px-4 py-4">
      <div className="max-w-7xl mx-auto">
        <div className="flex flex-col sm:flex-row gap-4 items-start sm:items-center">
          {/* Store Filter */}
          <div className="flex-1 min-w-0">
            <label htmlFor="store-filter" className="block text-sm font-medium text-gray-700 mb-1">
              Store
            </label>
            <select
              id="store-filter"
              value={filters.storeId === null ? 'null' : filters.storeId}
              onChange={handleStoreChange}
              className="w-full sm:w-48 px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            >
              {STORE_OPTIONS.map(option => (
                <option key={option.value} value={option.value === null ? 'null' : option.value}>
                  {option.label}
                </option>
              ))}
            </select>
          </div>

          {/* Date Range Picker */}
          <div className="flex-1 min-w-0">
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Date Range
            </label>
            <div className="flex flex-col sm:flex-row gap-2">
              <div className="flex-1">
                <DatePicker
                  selected={localStartDate}
                  onChange={(date) => setLocalStartDate(date)}
                  selectsStart
                  startDate={localStartDate}
                  endDate={localEndDate}
                  placeholderText="Start Date"
                  className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                />
              </div>
              <div className="flex-1">
                <DatePicker
                  selected={localEndDate}
                  onChange={(date) => setLocalEndDate(date)}
                  selectsEnd
                  startDate={localStartDate}
                  endDate={localEndDate}
                  minDate={localStartDate}
                  placeholderText="End Date"
                  className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default GlobalFilter;
