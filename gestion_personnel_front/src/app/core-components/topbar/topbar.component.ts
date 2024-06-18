import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { SigninRequest } from '../../models/signin-request/signin-request';
import { JwtResponse } from '../../models/jwt-response/jwt-response';
import { Observable, Subscription } from 'rxjs';
import { User } from '../../models/user/user';

@Component({
  selector: 'app-topbar',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './topbar.component.html',
  styleUrl: './topbar.component.css'
})
export class TopbarComponent implements OnInit {

  topbarTitle!: string;

  signinRequest!: SigninRequest;

  jwtResponse!: JwtResponse;
  jwtResponse$: Observable<JwtResponse> | undefined;

  subscriptionSignin!: Subscription;

  user!: User;
  user$: Observable<User> | undefined;
  subscriptionUser!: Subscription;

  constructor() {
    this.topbarTitle = "GTopbar title";
    this.signinRequest = new SigninRequest("","");
  }

  ngOnInit(): void {
      
  }



}
