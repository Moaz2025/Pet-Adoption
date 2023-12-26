import React from 'react'
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Outlet } from 'react-router-dom';

// Create your custom theme
const theme = createTheme({
  palette: {
    primary: {
      main: '#2196f3', // Your primary color
    },
    secondary: {
      main: '#ff4081', // Your secondary color
    },
  },
  // Add more customizations as needed
});
const Root = () => {
    return (
        <ThemeProvider theme={theme}>
          <Outlet />
        </ThemeProvider>
      );
}

export default Root
