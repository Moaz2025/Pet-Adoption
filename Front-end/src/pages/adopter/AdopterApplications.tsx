import { Card, CardContent, CircularProgress, Grid, Typography, Link as MuiLink } from '@mui/material';
import React, { useEffect, useState } from 'react'
import PetDisplay from '../../components/PetDisplay';
import { AdoptionAppliction } from '../../model/data';
import { Link, useNavigate } from 'react-router-dom';
import { getAllApplicationsOfAdopter } from '../../services/applications';
import { Pet } from '../../model/pet';
import { getPetById } from '../../services/data';

const AdopterApplications = () => {
  const [loading, setLoading] = useState(true);
  const [loadingPets, setLoadingPets] = useState(true);

  const [applications, setApplications] = useState<AdoptionAppliction[]>([]);
  const navigate = useNavigate();
  const [pets, setPets] = useState<Pet[]>([]);
  const onClickOnPet = (id:number) => {
    navigate(`/${id}`)
  }
  // useEffect(() => {
  //   // Fetch pet data for each application
  //   console.log("Init Apps");

  //   const fetchPetDetails = async () => {
  //     const petsList : Pet[] = [];
  //     applications.forEach(async (app) => {
  //       const pet : Pet = await getPetById(app.petId);
  //       console.log('Got', pet);
        
  //     })
      // const petDetails = await Promise.all(applications.map((app) => getPetById(app.petId)));
      // console.log('petGot', petDetails);
      
      // setPets(petDetails);
      // setLoadingPets(false);
  //     console.log("Loaded");
  //     console.log(pets);
      
  //   };

  //   fetchPetDetails();
  // }, [applications]);

  useEffect(()=>{
    console.log("Init");
    
    getAllApplicationsOfAdopter()
      .then(
        (value)=>{
          setApplications(value);
          setLoading(false)
          const petsList : Pet[] = [];
          applications.forEach((app) => {
            console.log(app);
            
            // const pet : Pet =  getPetById(app.petId);
            // console.log('Got', pet);
          })
        }
      )
  }, [])
  // const timer = setTimeout(async ()=>{
  //   console.log(applications.length);
    
  //   applications.forEach((app) => {
  //     console.log(app);
      
  //     // const pet : Pet =  getPetById(app.petId);
  //     // console.log('Got', pet);
  //   })
  //   const petDetails = await Promise.all(applications.map((app) => getPetById(app.petId)));
  //   console.log('petGot', petDetails);
    
  //   setPets(petDetails);
  //   setLoadingPets(false);
  // }, 1000)
  const getStatusColor = (status: string) => {
    switch (status) {
      case 'Accepted':
        return 'success.main';
      case 'Refused':
        return 'error.main';
      case 'Pending':
      default:
        return 'warning.main';
    }
  };

  const getPet = (id: number) : Pet => {
    const pet : Pet = {
      id: 0,
      shelterName: '',
      isBooked: false,
      name: '',
      species: '',
      breed: '',
      age: 2,
      behavior: '',
      description: '',
      houseTraining: true,
      gender: '',
      vaccination: '',
      spaying: '',
      neutering: ''
    };
    getPetById(id)
      .then((value) => {
        return value;
      })
      .catch((error) => {
        return pet;
      })
    return pet;
  }
  if (loading) {
    return (
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <CircularProgress />
      </div>
    );
  }
  return (
    <Grid container spacing={2}>
      {applications.map((application, index) => (
        <Grid item xs={12} key={application.appId}>
          <Card sx={{ display: 'flex' }}>
            {/* Left side: Pet Display */}
            {/* <PetDisplay pet={()=>getPetById(application.petId)} onClick={() => onClickOnPet(application.petId)} /> */}
            
            {/* {!loadingPets && <PetDisplay pet={pets[index]} onClick={() => onClickOnPet(application.petId)} />} */}
            {/* Right side: Application Status */}
            <CardContent sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
              <div>
                <Typography variant="h6" gutterBottom>
                  Application Status
                </Typography>
                <Typography variant="body1" color={getStatusColor(application.status)}>
                  {application.status}
                </Typography>
              </div>
              
              {/* Link on the right side */}
              <Grid sx={{margin:4}}>
                <MuiLink
                  component={Link}
                  to={`/adopter/${application.petId}`}
                  underline="none"
                  color="inherit"
                >
                  Go to Pet
                </MuiLink>
              </Grid>
            </CardContent>
          </Card>
        </Grid>
      ))}
    </Grid>
  );
}

export default AdopterApplications
