export interface LoginForm{
    email: string;
    password: string;
    userType: string;
}

export interface SignUpForm{
    email: string;
    password: string;
    name: string; 
    phone: string;
}

export interface UserCredentials{
    userType:string
    email:string
    token:string
}

export interface LoginResponse {
    status?: number
    message?: string
    userType?:string
    email?:string
    token?:string
}