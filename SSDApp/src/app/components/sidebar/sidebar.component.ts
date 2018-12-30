import { Component, OnInit } from '@angular/core';
import { CustomHttpService } from 'src/app/services/custom-http.service';
import { Game } from 'src/app/models/game';
import { Toast, ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private customHttp : CustomHttpService,
    private toastrService: ToastrService) { }
  
  allgames : Game[] = [];
  homeTeam : string;
  guestTeam : string;
  

  ngOnInit() {
    this.customHttp.get('/games').subscribe(
      (value : any) => {
        this.allgames = value;
        console.log(this.allgames);
      }
    )
  }

  addGame()
  {
    this.customHttp.post('/addGame', {homeTeam : this.homeTeam , guestTeam : this.guestTeam}).subscribe(
      (value : any)  => {
        if(value == true) this.toastrService.success("Successfully inserted game");
        else this.toastrService.error("Game was not inserted, please check again teams");
      }
    )
  }

}
