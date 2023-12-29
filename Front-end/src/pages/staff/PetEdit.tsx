import React, { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import { TextField, Button, Grid, Paper, Typography, Container, Switch, FormControl, InputLabel, Select, MenuItem, SelectChangeEvent, FormControlLabel, Checkbox, Dialog, DialogActions, DialogContent, DialogTitle, DialogContentText  } from '@mui/material';
import { useNavigate, useParams } from 'react-router-dom';
import { Pet } from '../../model/pet';
import { addAttachment, addPet, editPet, getPetById } from '../../services/data';
import { Attachment } from '../../model/data';

const PetEdit = () => {
  const navigate = useNavigate();
  const { id } = useParams();
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

  const [open, setOpen] = useState(false);
  const [inputValue, setInputValue] = useState('');

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleOk = () => {
    console.log(inputValue);
    const attachment : Attachment = {
      petId: parseInt(id!),
      attachment: inputValue
    }
    addAttachment(attachment)
      .then((value) => {
        alert(value);
        window.location.reload();
      }) 
      .catch((error) => {
        alert('Failed to add attachment')
        window.location.reload();
      })
    handleClose();
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  };

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

  useEffect(()=>{
    getPetById(parseInt(id!)).
      then((res)=>{
        console.log('Response ',res);
        setPetData(res);  
      });  
  }, [])
  
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
    console.log('Book data Eddited:', petData);
    editPet(petData, petData.id)
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
        <Button variant="outlined" color="primary" onClick={handleClickOpen}>
          Attach
        </Button>
        <Dialog open={open} onClose={handleClose}>
          <DialogTitle>Enter a Value</DialogTitle>
          <DialogContent>
            <DialogContentText>
              Please enter a value in the field below:
            </DialogContentText>
            <TextField
              autoFocus
              margin="dense"
              id="inputValue"
              label="Value"
              type="text"
              fullWidth
              value={inputValue}
              onChange={handleInputChange}
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClose} color="primary">
              Cancel
            </Button>
            <Button onClick={handleOk} color="primary">
              Ok
            </Button>
          </DialogActions>
        </Dialog>
      </Grid>
      <Grid item xs={12}>
        <Button onClick={handleSubmit} variant="contained" color="primary">
          Edit
        </Button>
      </Grid>
    </Grid>
  );
}

export default PetEdit
