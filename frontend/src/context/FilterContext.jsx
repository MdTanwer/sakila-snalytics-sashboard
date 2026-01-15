import React, { createContext, useContext, useState, useCallback } from 'react';
import { DATE_DEFAULTS, STORE_OPTIONS } from '../utils/constants';

const FilterContext = createContext();

export const useFilters = () => {
  const context = useContext(FilterContext);
  if (!context) {
    throw new Error('useFilters must be used within a FilterProvider');
  }
  return context;
};

export const FilterProvider = ({ children }) => {
  const [filters, setFilters] = useState({
    storeId: null,
    startDate: DATE_DEFAULTS.START_DATE,
    endDate: DATE_DEFAULTS.END_DATE,
  });

  const setStoreId = useCallback((id) => {
    setFilters(prev => ({ ...prev, storeId: id }));
  }, []);

  const setDateRange = useCallback((start, end) => {
    setFilters(prev => ({ ...prev, startDate: start, endDate: end }));
  }, []);

  const resetFilters = useCallback(() => {
    setFilters({
      storeId: null,
      startDate: DATE_DEFAULTS.START_DATE,
      endDate: DATE_DEFAULTS.END_DATE,
    });
  }, []);

  const value = {
    filters,
    setStoreId,
    setDateRange,
    resetFilters,
    STORE_OPTIONS,
  };

  return (
    <FilterContext.Provider value={value}>
      {children}
    </FilterContext.Provider>
  );
};
