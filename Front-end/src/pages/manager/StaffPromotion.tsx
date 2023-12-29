import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { StaffPromotion as form } from '../../model/shelter';
import { createShelter } from '../../services/shelter';
import { Card } from '@mui/material';
import { hireStaffMember } from '../../services/manager';


const StaffPromotion: React.FC = () => {
  const [formData, setFormData] = useState<form>({
    email: '',
    role: ''
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
    hireStaffMember(formData)
      .then((value) => {
        alert(value);
        window.location.reload();
      })
      .catch((error) => {
        alert('Failed to hire a staff member')
        window.location.reload();
      })
    // You can perform additional actions here, such as sending the data to a server
  };

  return (
    <Card sx={{margin: 1}}>
        <form onSubmit={handleSubmit} style={{padding: 4}}>
            <TextField
                label="Employee email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                fullWidth
                margin="normal"
                required
            />
            <TextField
                label="Employee role"
                name="role"
                value={formData.role}
                onChange={handleChange}
                fullWidth
                margin="normal"
                required
            />
            <Button type="submit" variant="contained" color="primary">
                Hire a staff member
            </Button>
        </form>
    </Card>
    
  );
};

export default StaffPromotion;