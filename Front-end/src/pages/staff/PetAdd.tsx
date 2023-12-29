import React, { ChangeEvent, FormEvent, useState } from 'react';
import { TextField, Button, Grid, Paper, Typography, Container, Switch  } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { Pet } from '../../model/pet';

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

  
  const handleInputChange = (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    console.log(e);
    
    setPetData((prevData: Pet) => ({
      ...prevData,
      [name]: value,
    }));
    
  };

  // const handleSelectChange = (e: ChangeEvent<{ value: unknown }>) => {
  //   const { name, value } = e.target as { name: string; value: unknown };
  //   setBookData((prevData: Book) => ({
  //     ...prevData,
  //     [name]: value,
  //   }));
  // };


  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    // Send the bookData to the backend for processing
    console.log('Book data submitted:', petData);
    
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
    <Container component="main" maxWidth="md">
      <Paper elevation={3} style={{ padding: '20px', margin: '20px' }}>
        <Typography variant="h5" align="center" gutterBottom>
          Add New Pet
        </Typography>
        <form onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Name"
                name="name"
                required
                value={petData.name}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Species"
                name="species"
                value={petData.species}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Breed"
                name="breed"
                required
                value={petData.breed}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={12}>
              <Switch
                checked={petData.isBooked}
                onChange={handleInputChange}
                inputProps={{ 'aria-label': 'controlled' }}
                />
              {/* <TextField
                fullWidth
                label="IsBooked"
                name="isBooked"
                type="radio"
                required
                value={petData.isBooked}
                onChange={handleInputChange}
              /> */}
            </Grid>
            {/* <Grid item xs={12}>
              <TextField
                fullWidth
                label="Author"
                name="author"
                required
                value={petData.author}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Category"
                name="category"
                type="text"
                value={petData.category}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Publisher"
                name="publisher"
                type="text"
                value={petData.publisher}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Pages Number"
                name="pagesNumber"
                type="number"
                value={petData.pagesNumber}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Cover Image Link"
                name="coverImageLink"
                value={petData.coverImageLink}
                onChange={handleInputChange}
              />
            </Grid>
            <Grid item xs={12}>
            <TextField
                fullWidth
                label="Publish Date"
                name="publishDate"
                type="date"
                value={petData.publishDate?.toISOString().split('T')[0] || ''}
                onChange={handleDateChange}
                InputLabelProps={{
                  shrink: true,
                }}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Amount"
                name="amount"
                type="number"
                required
                value={petData.amount}
                onChange={handleInputChange}
              />
            </Grid> */}
            <Grid item xs={12}>
              <Button type="submit" variant="contained" color="primary">
                Add Book
              </Button>
            </Grid>
          </Grid>
        </form>
      </Paper>
    </Container>
  );
}

export default PetAdd
