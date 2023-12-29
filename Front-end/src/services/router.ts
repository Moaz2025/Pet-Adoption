import { getUserCredentials, isAuthenticated } from "./auth"

export const router = ():string => {
    const isLoggedIn = isAuthenticated();
    if(!isLoggedIn){
        return '/'
    }
    const credits = getUserCredentials();
    console.log(credits);
    
    if(credits == null){
        return '/'
    }
    if(credits.userType.toLowerCase() == 'admin'){
        return '/admin'
    }
    if(credits.userType.toLowerCase() == 'adopter'){
        return '/adopter'
    }
    if(credits.userType.toLowerCase() == 'staff'){
        return '/staff'
    }
    return '/manager'
}