import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { Shelter } from '../../model/shelter';

const ShelterInfo : React.FC<{ shelter: Shelter }> = ({ shelter }) => {
  return (
    <Card>
      <CardContent>
        <Typography variant="h5" component="div">
          {shelter.name}
        </Typography>
        <Typography color="textSecondary">{shelter.location}</Typography>
        <Typography color="textSecondary">Phone: {shelter.phone}</Typography>
      </CardContent>
    </Card>
  )
}

export default ShelterInfo