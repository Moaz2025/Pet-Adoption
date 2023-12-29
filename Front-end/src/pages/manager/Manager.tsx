import React from 'react'
import { Outlet } from 'react-router-dom'
import CustomAppBar from '../../components/AppBar'

const Manager = () => {
  return (
    <div>
      <CustomAppBar />
      <Outlet />
    </div>
  )
}

export default Manager
