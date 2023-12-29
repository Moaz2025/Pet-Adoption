import React, { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import { TextField, Button, Grid, Paper, Typography, Container, Switch, FormControl, InputLabel, Select, MenuItem, SelectChangeEvent, FormControlLabel, Checkbox, List, ListItem, ListItemText  } from '@mui/material';
import { useNavigate, useParams } from 'react-router-dom';
import { Pet } from '../../model/pet';
import { addPet, editPet, getAllAttachments, getPetById } from '../../services/data';
import { AdoptionAppliction, Attachment } from '../../model/data';
import AttachmentList from '../../components/AttachmentList';
import { getUserCredentials } from '../../services/auth';
import { adoptPet } from '../../services/applications';

const PetDetails = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const [attachments, setAttachments] = useState<Attachment[]>([]);
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

    useEffect(()=>{
        getPetById(parseInt(id!)).
          then((res)=>{
            console.log('Response ',res);
            setPetData(res);  
          });
        getAllAttachments(parseInt(id!))
          .then((res)=>{
            console.log('Response ',res);
            setAttachments(res);  
          });
      }, [])

      const handleClickAdopt = () => {
        const application : AdoptionAppliction = {
          adopterEmail: getUserCredentials()?.email!,
          appId: 0,
          petId: parseInt(id!),
          status: 'Pending'
        }
        adoptPet(application) 
          .then((value) => {
            alert(value);
            window.location.reload();
          }) 
          .catch((error) => {
            alert('Failed to Adopt pet')
            window.location.reload();
          })
      }
      return (
        <Paper elevation={3} style={{ padding: '20px', maxWidth: '800px', margin: 'auto' }}>
          <Typography variant="h5" gutterBottom>
            Pet Details
          </Typography>
          <Grid container spacing={2}>
            <Grid item xs={12} md={6}>
              <List>
                <ListItem>
                  <ListItemText primary="Shelter Name" secondary={petData.shelterName} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Pet Name" secondary={petData.name} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Species" secondary={petData.species} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Breed" secondary={petData.breed} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Age" secondary={petData.age} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Behavior" secondary={petData.behavior} />
                </ListItem>
              </List>
            </Grid>
            <Grid item xs={12} md={6}>
              <List>
                <ListItem>
                  <ListItemText primary="Description" secondary={petData.description} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Gender" secondary={petData.gender} />
                </ListItem>
                <ListItem> 
                  <ListItemText primary="House Trained" secondary={petData.houseTraining ? 'Yes' : 'No'} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Is Booked" secondary={petData.isBooked ? 'Yes' : 'No'} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Vaccination" secondary={petData.vaccination} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Spaying" secondary={petData.spaying} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Neutering" secondary={petData.neutering} />
                </ListItem>
              </List>
            </Grid>
            <Grid sx={{margin: 4}} maxWidth={'80%'}>
              <Typography variant="h6" gutterBottom>
                Pet Attachments
              </Typography>
              <AttachmentList attachments={attachments}/>
            </Grid>
            <Grid sx={{margin: 4}} maxWidth={'50%'}>
              <Button onClick={handleClickAdopt} variant="contained" color="primary">
                Adopt
              </Button>
            </Grid>
          </Grid>
        </Paper>
      );
}

export default PetDetails
