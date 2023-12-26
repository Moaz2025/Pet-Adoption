import React, { useState } from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { Grid, Paper } from '@mui/material';

interface Shelter {
  name: string;
  location: string;
  phone: string;
}

interface EditableShelterDetailsProps {
  shelter: Shelter;
  onUpdate: (updatedShelter: Shelter) => void;
}

const EditShelter: React.FC<EditableShelterDetailsProps> = ({ shelter, onUpdate }) => {
  const [editableShelter, setEditableShelter] = useState({ ...shelter });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setEditableShelter((prevShelter) => ({
      ...prevShelter,
      [name]: value,
    }));
  };

  const handleSubmit = () => {
    onUpdate(editableShelter);
  };

  return (
    <Card sx={{margin: 1}}>
      <CardContent>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <Typography variant="h5" component="div">
              <TextField label="Name" name="name" value={editableShelter.name} onChange={handleChange} fullWidth />
            </Typography>
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField label="Location" name="location" value={editableShelter.location} onChange={handleChange} fullWidth />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField  label="Phone" name="phone" value={editableShelter.phone} onChange={handleChange} fullWidth />
          </Grid>
        </Grid>
        <Button sx={{m:1}} variant="contained" color="primary" onClick={handleSubmit}>
          Edit Shelter
        </Button>
      </CardContent>
    </Card>
  );
};

export default EditShelter;