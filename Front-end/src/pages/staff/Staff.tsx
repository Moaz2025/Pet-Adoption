import React from 'react'
import { Outlet } from 'react-router-dom'
import CustomAppBar from '../../components/AppBar'

const Staff = () => {
  return (
    <div>
      <CustomAppBar />
      <Outlet />
    </div>
  )
}

export default Staff
