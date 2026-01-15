import { format, formatDistanceToNow, parseISO } from 'date-fns';

export const formatCurrency = (amount) => {
  return new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
  }).format(amount);
};

export const formatDate = (dateString) => {
  const date = parseISO(dateString);
  return format(date, 'MMM dd, yyyy');
};

export const formatRelativeTime = (dateString) => {
  const date = parseISO(dateString);
  const now = new Date();
  const diffInHours = (now - date) / (1000 * 60 * 60);
  
  if (diffInHours < 24) {
    return formatDistanceToNow(date, { addSuffix: true });
  }
  
  return formatDate(dateString);
};

export const getInitials = (fullName) => {
  return fullName
    .split(' ')
    .map(word => word[0])
    .join('')
    .toUpperCase()
    .slice(0, 2);
};

export const formatPercentage = (decimal) => {
  return `${Math.round(decimal * 100)}%`;
};
