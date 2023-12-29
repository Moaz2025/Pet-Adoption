import axios, { AxiosResponse } from "axios";
import { Shelter, ShelterCreation, StaffPromotion } from "../model/shelter";
import { getUserCredentials } from "./auth";
import { Pet } from "../model/pet";
import { AdoptionAppliction, Attachment } from "../model/data";

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

export const addAttachment = async (attachment: Attachment): Promise<string> => {
  try {
    const token = getUserCredentials()?.token; // Replace with your actual access token
    const response: AxiosResponse = await axios.post(`${API_URL}/pet/attach`, attachment, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
    });
    // Handle the response data here
    console.log(response.data);
    return (response.data);
  } catch (error) {
    // Handle errors here
    console.error('Error Adding Attachment:', error);
    return 'error Failed to add attachment'
  }
}
export const getAllAttachments = async (id: number): Promise<Attachment[]> => {
  try {
      const token = getUserCredentials()?.token; // Replace with your actual access token
      const response: AxiosResponse<Attachment[]> = await axios.get(`${API_URL}/pet/getAllAttachments/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      
      // Handle the response data here
      console.log(response.data);
      return response.data ? response.data : [];
    } catch (error) {
      // Handle errors here
      console.error('Error fetching data:', error);
      return [];
    }
};

export const addPet = async (pet: Pet): Promise<string> => {
    try {
      const token = getUserCredentials()?.token; // Replace with your actual access token
      const response: AxiosResponse = await axios.post(`${API_URL}/pet/add`, pet, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });
      // Handle the response data here
      console.log(response.data);
      return (response.data);
    } catch (error) {
      // Handle errors here
      console.error('Error Adding Pet:', error);
      return 'error Failed to add'
    }
}

export const getAllPets = async (): Promise<Pet[]> => {
    try {
        const token = getUserCredentials()?.token; // Replace with your actual access token
        const response: AxiosResponse<Pet[]> = await axios.get(`${API_URL}/pet/getAllPets`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        
        // Handle the response data here
        console.log(response.data);
        return response.data ? response.data : [];
      } catch (error) {
        // Handle errors here
        console.error('Error fetching data:', error);
        return [];
      }
};

export const getPetById = async (id:number): Promise<Pet> => {
  try {
      const token = getUserCredentials()?.token; // Replace with your actual access token
      const response: AxiosResponse<Pet> = await axios.get(`${API_URL}/pet/getPetById?id=${id}`, {
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
        name: 'no cat found',
        shelterName: 'none',
        id: 5,
        species: 'none',
        breed: 'none',
        isBooked: false,
        age: 5,
        behavior: 'none',
        description: 'none',
        houseTraining: false,
        gender: 'none',
        vaccination: 'none',
        spaying: 'none',
        neutering: 'none'
      }
    }
};

export const editPet = async (pet: Pet, id: number): Promise<string> => {
  try {
    const token = getUserCredentials()?.token; // Replace with your actual access token
    const response: AxiosResponse = await axios.put(`${API_URL}/pet/edit/${id}`, pet, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
    });
    // Handle the response data here
    console.log(response.data);
    return (response.data);
  } catch (error) {
    // Handle errors here
    console.error('Error Editting Pet:', error);
    return 'error Failed to edit'
  }
}

export const deletePet = async (id: number): Promise<string> => {
  try {
    const token = getUserCredentials()?.token; // Replace with your actual access token
    const response: AxiosResponse = await axios.delete(`${API_URL}/Pet/delete/${id}`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
    });
    // Handle the response data here
    console.log(response.data);
    return (response.data);
  } catch (error) {
    // Handle errors here
    console.error('Error Deleting A Pet:', error);
    return 'error Failed to delete'
  }
}