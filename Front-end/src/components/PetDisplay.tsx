import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { Box, CardActionArea } from '@mui/material';

import { Pet } from '../model/pet'

interface PetDisplayProps {
    pet:Pet;
    onClick: ()=> any
}

const BookDisplay:React.FC<PetDisplayProps> = ({pet, onClick}) => {
    return (
        <Card sx={{ minWidth:140, width: 300, m: 1 }}>
          <CardActionArea onClick={onClick}>
            <CardContent>
              <Typography gutterBottom variant="h5" component="div">
                {pet.name}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                {pet.species}
              </Typography>
              <Typography gutterBottom variant="h5" component="div">
                {pet.age.toFixed(2) + ' year'}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                {pet.gender}
              </Typography>
            </CardContent>
          </CardActionArea>
        </Card>
      );
}

export default BookDisplay
