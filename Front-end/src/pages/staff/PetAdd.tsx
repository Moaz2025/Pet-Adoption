import React, { ChangeEvent, FormEvent, useState } from 'react';
import { TextField, Button, Grid, Paper, Typography, Container, Switch, FormControl, InputLabel, Select, MenuItem, SelectChangeEvent, FormControlLabel, Checkbox  } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { Pet } from '../../model/pet';
import { addPet } from '../../services/data';

const PetAdd = () => {
  const navigate = useNavigate();
  const [petData, setPetData] = useState<Pet>({
    name: '',
    shelterName: '',
    id: 0,
    species: '',
    breed: '',
    isBooked: false,
    age: 0,
    behavior: '',
    description: '',
    houseTraining: false,
    gender: '',
    vaccination: '',
    spaying: '',
    neutering: ''
  });

  
  const handleChange = (e: React.ChangeEvent<HTMLInputElement | { name?: string; value: unknown }>) => {
    const { name, value } = e.target;
    setPetData((prevPet) => ({
      ...prevPet,
      [name as string]: value,
    }));
  };

  const handleChangeSelect = (e: SelectChangeEvent<string>) => {
    const { name, value } = e.target;
    setPetData((prevPet) => ({
      ...prevPet,
      [name as string]: value,
    }));
  };

  const handleChangeHouseTraining = (e: SelectChangeEvent<string> | React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    if (name === 'houseTraining') {
      // Use type assertion to inform TypeScript about the expected shape
      const checkboxValue = (e.target as HTMLInputElement).checked;

      setPetData((prevPet) => ({
        ...prevPet,
        [name]: checkboxValue,
      }));
    }
  };
  // const handleSelectChange = (e: ChangeEvent<{ value: unknown }>) => {
  //   const { name, value } = e.target as { name: string; value: unknown };
  //   setBookData((prevData: Book) => ({
  //     ...prevData,
  //     [name]: value,
  //   }));
  // };


  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    // Send the bookData to the backend for processing
    console.log('Book data submitted:', petData);
    addPet(petData)
      .then((value) => {
        alert(value);
        window.location.reload();
      })
      .catch((error) => {
        alert('Failed to add pet')
        window.location.reload();
      })
    // addBook(bookData)
    //   .then((message)=>{
    //     if(message.includes('error')){
    //       notify(message.substring(6), 'error')
    //     }else{
    //       notify(message, 'success')
    //     }
    //   });
    // You can add an API call here to send the data to the backend
  };

  return (
    <Grid container spacing={2}>
      <Grid item xs={12}>
        <TextField label="Pet Name" name="name" value={petData.name} onChange={handleChange} fullWidth required />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField label="Species" name="species" value={petData.species} onChange={handleChange} fullWidth required />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField label="Breed" name="breed" value={petData.breed} onChange={handleChange} fullWidth required />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField type="number" label="Age" name="age" value={petData.age} onChange={handleChange} fullWidth required />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField label="Behavior" name="behavior" value={petData.behavior} onChange={handleChange} fullWidth required />
      </Grid>
      <Grid item xs={12}>
        <TextField
          label="Description"
          name="description"
          value={petData.description}
          onChange={handleChange}
          multiline
          rows={4}
          fullWidth
          required
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <FormControl fullWidth>
          <InputLabel>Gender</InputLabel>
          <Select name="gender" value={petData.gender} onChange={handleChangeSelect} required>
            <MenuItem value="Male">Male</MenuItem>
            <MenuItem value="Female">Female</MenuItem>
          </Select>
        </FormControl>
      </Grid>
      <Grid item xs={12} sm={6}>
        <FormControlLabel
          control={
            <Checkbox
              checked={petData.houseTraining}
              onChange={handleChangeHouseTraining}
              name="houseTraining"
              color="primary"
            />
          }
          label="House Trained"
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField label="Vaccination" name="vaccination" value={petData.vaccination} onChange={handleChange} fullWidth />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField label="Spaying" name="spaying" value={petData.spaying} onChange={handleChange} fullWidth />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField label="Neutering" name="neutering" value={petData.neutering} onChange={handleChange} fullWidth />
      </Grid>
      <Grid item xs={12}>
        <Button onClick={handleSubmit} variant="contained" color="primary">
          Add Pet
        </Button>
      </Grid>
    </Grid>
  );
}

export default PetAdd