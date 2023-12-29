import axios, { AxiosResponse } from "axios";
import { LoginForm, LoginResponse, SignUpForm, UserCredentials } from "../model/auth";
import { ShelterCreation } from "../model/shelter";
import { getUserCredentials } from "./auth";
 
const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';
const userCredentialsNameInStorage = 'credentials'
// import { getUserCredentials } from "./auth";
// import { ShelterCreation } from "../model/shelter";

// export const createShelter = async (shelter: ShelterCreation): Promise<string> => {
//     try {
//       const token = getUserCredentials()?.token; // Replace with your actual access token
//       const response: AxiosResponse<string> = await axios.post(`${API_URL}/promotion/promoteToManager`, shelter.email, {
//         headers: {
//           'Content-Type': 'application/json',
//           Authorization: `Bearer ${token}`,
//         },
//       });
//       // Handle the response data here
//       console.log(response.data);
//       return (response.data);
//     } catch (error: any) {
//       // Handle errors here
//       console.error('Error Creating a shelter: ', error);
//       if(error.response){
//         return error.response.data ?  error.response.data : 'Error creating a shelter'
//       }
//       return 'Error creating a shelter'
//     }
// }
export const createShelter = async (shelter: ShelterCreation): Promise<string> => {
  try {
    const token = getUserCredentials()?.token; // Replace with your actual access token
    const response: AxiosResponse<string> = await axios.post(`${API_URL}/promotion/promoteToManager`, shelter, {
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
    console.error('Error Creating a shelter: ', error);
    if(error.response){
      return error.response.data ?  error.response.data : 'Error creating a shelter'
    }
    return 'Error creating a shelter'
  }
}