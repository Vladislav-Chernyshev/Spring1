import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Page} from "../model/page";

@Component({
  selector: 'app-user-service',
  templateUrl: './user-service.component.html',
  styleUrls: ['./user-service.component.scss']
})
export class UserServiceComponent {

  constructor(private http: HttpClient) {
  }

  public findAll() {
    return this.http.get<Page>("api/v1/user")
  }


}
