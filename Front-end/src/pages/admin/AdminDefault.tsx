import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { ShelterCreation } from '../../model/shelter';
import { createShelter } from '../../services/shelter';


const AdminDefault: React.FC = () => {
  const [formData, setFormData] = useState<ShelterCreation>({
    email: '',
    name: '',
    location: '',
    phone: '',
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    console.log('Form Data:', formData);
    createShelter(formData)
      .then((value) => {
        alert(value);
      })
    // You can perform additional actions here, such as sending the data to a server
  };

  return (
    <form onSubmit={handleSubmit}>
      <TextField
        label="Manager Email"
        name="email"
        value={formData.email}
        onChange={handleChange}
        fullWidth
        margin="normal"
        required
      />
      <TextField
        label="Shelter Name"
        name="name"
        value={formData.name}
        onChange={handleChange}
        fullWidth
        margin="normal"
        required
      />
      <TextField
        label="Shelter Location"
        name="location"
        value={formData.location}
        onChange={handleChange}
        fullWidth
        margin="normal"
        required
      />
      <TextField
        label="Phone"
        name="phone"
        value={formData.phone}
        onChange={handleChange}
        fullWidth
        margin="normal"
        required
      />
      <Button type="submit" variant="contained" color="primary">
        Create a Shelter
      </Button>
    </form>
  );
};

export default AdminDefault;