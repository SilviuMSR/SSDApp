import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { CustomHttpService } from 'src/app/services/custom-http.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  users : User[] = [];
  cname : string;
  cpassword : string;
  cteam : string;

  constructor(private http: CustomHttpService,
              private router: Router ) { }

  ngOnInit() {
  }

  saveUserToDb(){
    this.http.post('/saveUser', {name: this.cname, password: this.cpassword, teamname: this.cteam}).subscribe(value => {
      console.log(value);
      if(value)
      {
        console.log("Succcess");
      }
      else
      {
        console.log("Failed");
      }
    });
  }

}
