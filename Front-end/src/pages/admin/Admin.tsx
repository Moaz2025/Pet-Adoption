import React, { useEffect, useState } from 'react'
import { router } from '../../services/router';
import { Outlet, useNavigate } from 'react-router-dom';
import LoadingCircle from '../../components/LoadingCircle';
import CustomAppBar from '../../components/AppBar';

const Admin = () => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(true);
    useEffect(()=>{
        const checkAuthentication = async () => {
          const route = router();
          if (route != '/admin') {
            navigate(route);
          }
          setLoading(false);
        };
        checkAuthentication()
      }, [])
      if (loading) {
        <LoadingCircle></LoadingCircle>
      }
  return (
    <div >
      <CustomAppBar />
      <h1 style={{textAlign: 'center'}} >Admin</h1>
      <h2 style={{textAlign: 'center'}} >Create a shelter and set its manager</h2>
      <Outlet />
    </div>
  )
}

export default Admin
