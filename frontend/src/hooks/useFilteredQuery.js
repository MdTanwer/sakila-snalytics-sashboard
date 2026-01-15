import { useQuery } from '@apollo/client/react';
import { useFilters } from '../context/FilterContext';

export const useFilteredQuery = (query, mockDataKey) => {
  const { filters } = useFilters();
  
  // Only include non-null parameters to avoid backend date filtering issues
  const variables = {};
  if (filters.storeId !== null) {
    variables.storeId = filters.storeId;
  }
  // Temporarily exclude date parameters due to backend issues
  // if (filters.startDate) {
  //   variables.startDate = filters.startDate;
  // }
  // if (filters.endDate) {
  //   variables.endDate = filters.endDate;
  // }
  
  const { loading, error, data, refetch } = useQuery(query, {
    variables,
  });

  return {
    loading,
    error,
    data,
    refetch,
  };
};
