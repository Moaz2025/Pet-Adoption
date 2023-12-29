import React, { MouseEvent, useContext } from 'react';
import { AppBar, Toolbar, Typography, Button, IconButton, Avatar, createTheme } from '@mui/material';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import HomeIcon from '@mui/icons-material/Home';
import InfoIcon from '@mui/icons-material/Info';
import SettingsBrightnessIcon from '@mui/icons-material/SettingsBrightness';
import { useNavigate } from 'react-router-dom';
import { logout } from '../services/auth';


interface CustomAppBarProps {
  onLogout?: () => any;
  appName?: string;
}

const appName = 'Pets Home'
const CustomAppBar: React.FC<CustomAppBarProps> = () => {
  const theme = createTheme();
  const navigate = useNavigate();
  const handleLogout = (event: MouseEvent) => {
    event.preventDefault();
    logout()
      .then(()=>{
        navigate('/');
    });
  };

  const handleVisitHome = (event: MouseEvent) => {
    event.preventDefault();
    navigate('/')
  };

  const handleVisitAbout = (event: MouseEvent) => {
    event.preventDefault();
    navigate('about')
  };


  return (
    <AppBar position="static" sx={{mb:2}}>
      <Toolbar>
        {/* Left side - Log out button */}
        <IconButton
          sx={{m:0.2}}
          edge="start"
          color="inherit"
          aria-label="logout"
          onClick={handleLogout}
          size='large'
        >
          <ExitToAppIcon />
        </IconButton>

        <IconButton
          sx={{m:0.2}}
          edge="start"
          color="inherit"
          aria-label="logout"
          onClick={handleVisitHome}
          size='large'
        >
          <HomeIcon />
        </IconButton>
        {/* Center - App Name */}
        <Typography variant="h4" fontFamily={'cursive'} textAlign="center" component="div" sx={{ flexGrow: 1 }}>
          {appName}
        </Typography>
      </Toolbar>
    </AppBar>
  );
};

export default CustomAppBar;