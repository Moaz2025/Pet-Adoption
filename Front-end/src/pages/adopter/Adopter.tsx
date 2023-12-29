import React from 'react'
import { Outlet } from 'react-router-dom'
import CustomAppBar from '../../components/AppBar'

const Adopter = () => {
  return (
    <div>
      <CustomAppBar />
      <Outlet />
    </div>
  )
}

export default Adopter
