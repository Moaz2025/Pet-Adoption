import { CircularProgress, Divider, IconButton, Paper, Stack, TextField } from '@mui/material'
import AddCircleIcon from '@mui/icons-material/AddCircle';

import React, { useEffect, useState } from 'react'
import { Shelter } from '../../model/shelter'
import ShelterInfo from '../manager/ShelterInfo'
import CustomAppBar from '../../components/AppBar'
import PetDisplay from '../../components/PetDisplay';
import EditShelter from '../manager/EditShelter'
import StaffPromotion from '../manager/StaffPromotion'
import { editShelter, getShelter } from '../../services/manager'
import { Pet } from '../../model/pet';
import { useNavigate } from 'react-router-dom';
import { router } from '../../services/router';
import { getAllPets } from '../../services/data';

const StaffDefault = () => {
  const [loading, setLoading] = useState(true);
  const [pets, setPets] = useState<Pet[]>([]);
  const [filteredPets, setFilteredPets] = useState<Pet[]>([]);
  const [speciesFilter, setSpeciesFilter] = useState('');
  const [shelterFilter, setShelterFilter] = useState('');
  const navigate = useNavigate();
  
  const handleClickAddPet = () => {
    navigate('add')
  }

  const handleClickViewApplications = () => {
    navigate('applications')
  }

  const onClickOnPet = (id:number) => {
    navigate(`${id}`)
  }

  useEffect(()=>{
    const checkAuthentication = async () => {
      const route = router();
      if (route != '/staff') {
        navigate(route);
      }
      setLoading(false);
    };
    checkAuthentication()
    getAllPets()
      .then(
        (value)=>{
          setLoading(false)
          setPets(value);
          setFilteredPets(value);
        }
      )
  }, [])

  useEffect(() => {
    // Filter the books based on the current search criteria
    const filtered = pets.filter((pet) => {
      const speciesMatches = pet.species.toLowerCase().includes(speciesFilter.toLowerCase());
      const shelterMatches = pet.shelterName.toLowerCase().includes(shelterFilter.toLowerCase());

      return speciesMatches && shelterMatches;
    });

    setFilteredPets(filtered);
  }, [speciesFilter, shelterFilter]);
  
  if (loading) {
    return (
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <CircularProgress />
      </div>
    );
  }
  
  return (
    <div>
      <Stack sx={{margin:1}} width={"100%"} spacing={{ xs: 1, sm: 2 }} direction="row" useFlexGap flexWrap="wrap">
          <IconButton
              sx={{m:0.2}}
              edge="end"
              color="inherit"
              aria-label="pet"
              size='medium'
              onClick={handleClickAddPet}
            >
              {'Add Pet'} 
              <AddCircleIcon sx={{marginX:1}} />
          </IconButton>
          <IconButton
              sx={{m:0.2}}
              edge="end"
              color="inherit"
              aria-label="application"
              size='medium'
              onClick={handleClickViewApplications}
            >
              {'View Applications'} 
          </IconButton>
          <TextField
            label="Species"
            value={speciesFilter}
            onChange={(e) => setSpeciesFilter(e.target.value)}
          />
          {/* <TextField
            label="Shelter"
            value={shelterFilter}
            onChange={(e) => setShelterFilter(e.target.value)}
          /> */}
        </Stack>
        
        <Stack width={"100%"} spacing={{ xs: 1, sm: 2 }} direction="row" useFlexGap flexWrap="wrap">
          {filteredPets.map((pet) => 
            <PetDisplay pet={pet} onClick={()=>onClickOnPet(pet.id? pet.id : 1)}/>
          )}
        </Stack>
    </div>
    
  )
}

export default StaffDefault
