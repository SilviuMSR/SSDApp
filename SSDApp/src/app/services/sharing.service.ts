import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharingService {

  
  adminCheck: boolean = true;
  loggedIn: boolean = false;

  constructor() { 
    
  }

  get isLoggedInAndAdmin()
  {
    if(this.loggedIn == true && this.adminCheck == true)
      return true;
    else return false;
  }

  get isLoggedInAndCoach()
  {
    if(this.loggedIn == true && this.adminCheck == false)
      return true;
    else return false;
  }

  setAdminOptions()
  {
    this.adminCheck = true;
  }

  setUserOptions()
  {
    this.adminCheck = false;
  }

  setLoggedIn()
  {
    this.loggedIn = true;
  }

  setLoggedOut()
  {
    this.loggedIn = false;
  }
}
