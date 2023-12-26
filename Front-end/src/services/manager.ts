import axios, { AxiosResponse } from "axios";
import { Shelter, ShelterCreation, StaffPromotion } from "../model/shelter";
import { getUserCredentials } from "./auth";

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

export const getShelter = async (): Promise<Shelter> => {
    try {
        const token = getUserCredentials()?.token; // Replace with your actual access token
        const response: AxiosResponse<Shelter> = await axios.get(`${API_URL}/shelter/get`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        
        // Handle the response data here
        console.log(response.data);
        return response.data;
      } catch (error) {
        // Handle errors here
        console.error('Error fetching data:', error);
        return {
            name: 'no shelter found',
            location: '-----',
            phone: '00000'
        }
      }
};

export const editShelter = async (shelter: Shelter): Promise<string> => {
    try {
      const token = getUserCredentials()?.token; // Replace with your actual access token
      const response: AxiosResponse = await axios.put(`${API_URL}/data/update`, shelter, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });
      // Handle the response data here
      console.log(response.data);
      return (response.data.message);
    } catch (error) {
      // Handle errors here
      console.error('Error Editting shelter: ', error);
      return 'error Failed to edit shelter'
    }
}

export const hireStaffMember = async (form: StaffPromotion): Promise<string> => {
    try {
      const token = getUserCredentials()?.token; // Replace with your actual access token
      const response: AxiosResponse<string> = await axios.post(`${API_URL}/staff/promote`, form, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });
      // Handle the response data here
      console.log(response.data);
      return (response.data);
    } catch (error: any) {
      // Handle errors here
      console.error('Error Hire a staff member: ', error);
      if(error.response){
        return error.response.data ?  error.response.data : 'Error Hire a staff member'
      }
      return 'Error Hire a staff member'
    }
}