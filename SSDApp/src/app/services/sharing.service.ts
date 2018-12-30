import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharingService {

  
  adminCheck: boolean = true;
  loggedIn: boolean = false;

  constructor() { 
    
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
