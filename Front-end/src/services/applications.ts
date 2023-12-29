import axios, { AxiosResponse } from "axios";
import { Shelter, ShelterCreation, StaffPromotion } from "../model/shelter";
import { getUserCredentials } from "./auth";
import { Pet } from "../model/pet";
import { AdoptionAppliction, Attachment } from "../model/data";

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

export const adoptPet = async (pet: AdoptionAppliction): Promise<string> => {
    try {
      const token = getUserCredentials()?.token; // Replace with your actual access token
      const response: AxiosResponse = await axios.post(`${API_URL}/application/requestAdoption`, pet, {
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
      console.error('Error Adopt Pet:', error);
      return 'error Failed to adopt'
    }
}

export const getAllApplicationsOfShelter = async (): Promise<AdoptionAppliction[]> => {
    try {
        const token = getUserCredentials()?.token; // Replace with your actual access token
        const response: AxiosResponse<AdoptionAppliction[]> = await axios.get(`${API_URL}/application/list`, {
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

  export const getAllApplicationsOfAdopter = async (): Promise<AdoptionAppliction[]> => {
    try {
        const token = getUserCredentials()?.token; // Replace with your actual access token
        const response: AxiosResponse<AdoptionAppliction[]> = await axios.get(`${API_URL}/application/getAllMyApplication`, {
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

  export const acceptApplication = async (id: number): Promise<string> => {
    try {
      const token = getUserCredentials()?.token; // Replace with your actual access token
    //   console.log('Token', token);
      console.log('ID = ', id);
      const response: AxiosResponse = await axios.put(`${API_URL}/application/accept/${id}`, null, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });
    //   const response: AxiosResponse<string> = await axios.put(`${API_URL}/application/accept/${id}`, {
    //     headers: {
    //     //   'Content-Type': 'application/json',
    //       Authorization: `Bearer ${token}`,
    //     },
    //   });
      console.log(response);
      
      // Handle the response data here
      console.log(response.data);
      return (response.data);
    } catch (error) {
      // Handle errors here
      console.error('Error Accept application:', error);
      return 'error Failed to accept'
    }
}
export const rejectApplication = async (id: number): Promise<string> => {
    try {
      console.log('ID = ', id);
      const token = getUserCredentials()?.token; // Replace with your actual access token
      const response: AxiosResponse = await axios.put(`${API_URL}/application/refuse/${id}`,null, {
        headers: {
        //   'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });
      // Handle the response data here
      console.log(response.data);
      return (response.data);
    } catch (error) {
      // Handle errors here
      console.error('Error Reject Application:', error);
      return 'error Failed to reject'
    }
}
