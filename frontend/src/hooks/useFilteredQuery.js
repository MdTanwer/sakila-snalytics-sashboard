import { useQuery } from '@apollo/client/react';
import { useFilters } from '../context/FilterContext';
import { MOCK_MODE, mockQueries } from '../graphql/mockData';

export const useFilteredQuery = (query, mockDataKey) => {
  const { filters } = useFilters();
  
  const { loading, error, data, refetch } = useQuery(query, {
    variables: {
      storeId: filters.storeId,
      startDate: filters.startDate,
      endDate: filters.endDate,
      limit: 10, // Default limit for queries that need it
    },
    skip: MOCK_MODE,
  });

  const displayData = MOCK_MODE ? mockQueries[mockDataKey] : data;

  return {
    loading: MOCK_MODE ? false : loading,
    error: MOCK_MODE ? null : error,
    data: displayData,
    refetch,
  };
};
