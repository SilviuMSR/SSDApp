import { Component, OnInit} from '@angular/core';
import { SharingService } from '../../services/sharing.service';
import { CustomHttpService } from 'src/app/services/custom-http.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  cname : string;
  cpassword : string;

  constructor(private sharingService: SharingService,
    private customHttp: CustomHttpService) {
  }


  ngOnInit() {
  }

  LoggedIn()
  {
    this.customHttp.post('/logUser', {name: this.cname, password: this.cpassword}).subscribe(
      (value: any) => {
        if(value == 0)
        {
          this.sharingService.setAdminOptions();
          this.sharingService.setLoggedIn();
        }
        else if(value == 1)
        {
          this.sharingService.setUserOptions();
          this.sharingService.setLoggedIn();
        }
        else if(value == -1)
        {
          console.log("Not found");
        }
        console.log(value);
      }
    )
  }

}
