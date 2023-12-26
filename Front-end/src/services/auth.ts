import axios, { AxiosResponse } from "axios";
import { LoginForm, LoginResponse, SignUpForm, UserCredentials } from "../model/auth";

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';
const userCredentialsNameInStorage = 'credentials'

export const signUp = async (form: SignUpForm): Promise<void> => {
    try {
        const response: AxiosResponse = await axios.post(`${API_URL}/auth/signup`,
            JSON.stringify(form),
            {
                headers: { 'Content-Type': 'application/json' },
                withCredentials: true 
            } 
        );
        const status = response.status;
        if(status < 300){
            window.alert(`Signed up successfully/n${response.data}`)
            return;
        }
        window.alert(`Sign up failed/n${response.data}`)
        return;
      } catch (error: any) {
        console.error('Signup error:', error);
        window.alert("Sign up failed")
      }
};

export const login = async (form: LoginForm): Promise<LoginResponse> => {
    try{
        const response: AxiosResponse<LoginResponse> = await axios.post(`${API_URL}/auth/login`,
            JSON.stringify(form),
            {
                headers: { 'Content-Type': 'application/json' },
                withCredentials: true 
            } 
        );
        
        const authResponse: LoginResponse = {
            status: response.status!,
            email: response.data.email!,
            userType: response.data.userType!,
            token: response.data.token!,
            message: response.data.message!
        };
        
        const credits: UserCredentials = {
            email: authResponse.email!,
            userType: authResponse.userType!,
            token: authResponse.token!
        };
        
        localStorage.setItem(userCredentialsNameInStorage, JSON.stringify(credits));
        
        return authResponse;
    }catch(error:any){
        console.error('Login Failure: ', error);
        const authResponse: LoginResponse = {
            status: error.response.status,
            message: error.response.data.message,
        };
        return authResponse
    }

}


export const logout = async (): Promise<void> => {
    const credits = JSON.parse(localStorage.getItem(userCredentialsNameInStorage)!);
    try{
        console.log("credits = logout ,", credits);
        
        const response = await axios.post(`${API_URL}/auth/logout`,
            JSON.stringify(credits),
            {
                headers: { 'Content-Type': 'application/json' },
                withCredentials: true 
            } 
        );
        console.log(response);
        localStorage.removeItem(userCredentialsNameInStorage);
    }catch(error:any){
        console.error(error)
    }
};

export const isAuthenticated = (): boolean => {
    const credits = localStorage.getItem(userCredentialsNameInStorage);

    return !credits?.toLowerCase().includes('undefined');
};

export const getUserCredentials = (): UserCredentials | null => {
    if(isAuthenticated()){
        const credits: UserCredentials = JSON.parse(localStorage.getItem(userCredentialsNameInStorage)!);
        return credits
    }
    return null;
};