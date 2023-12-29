import { CircularProgress, Divider, Paper, Stack } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { Shelter } from '../../model/shelter'
import ShelterInfo from './ShelterInfo'
import CustomAppBar from '../../components/AppBar'
import EditShelter from './EditShelter'
import StaffPromotion from './StaffPromotion'
import { editShelter, getShelter } from '../../services/manager'
import { error } from 'console'

const ManagerDefault = () => {
  const [loading, setLoading] = useState(true);

  const [shelter, setShelter] = useState<Shelter>({
    name: 'Alex pet house',
    location: 'Alexandria',
    phone: '015536879'
  });

  useEffect(() => {
    getShelter()
      .then((value) => {
        setShelter(value);
        setLoading(false);
      })
  }, [])

  const onEdit = (shelter: Shelter) => {
    console.log(shelter);
    editShelter(shelter)
      .then((value) => {
        alert(value);
        window.location.reload();
      })
      .catch((error) => {
        alert('Failed to edit shelter')
        window.location.reload();
      })
  }
  if (loading) {
    return (
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <CircularProgress />
      </div>
    );
  }
  
  return (
    <div>
      <Paper sx={{marginY: 2}}>
        <ShelterInfo shelter={shelter}></ShelterInfo>
      </Paper>
      <Stack direction={"row"}>
        <EditShelter shelter={shelter} onUpdate={onEdit}/>
        <StaffPromotion />
      </Stack>
    </div>
    
  )
}

export default ManagerDefault
