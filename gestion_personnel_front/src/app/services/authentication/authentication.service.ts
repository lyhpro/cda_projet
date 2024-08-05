import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SigninRequest } from '../../models/signin-request/signin-request';
import { Observable } from 'rxjs';
import { JwtResponse } from '../../models/jwt-response/jwt-response';

const API_URL_AUTH = 'http://localhost:8080/gestionnaire-personnel/authentication/';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) {}

  signin(signinRequest: SigninRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(API_URL_AUTH + 'signin', signinRequest);
  }

  activatedUser(tokenId: number): Observable<boolean> {
    return this.http.get<boolean>(API_URL_AUTH + 'activatedUser/' + tokenId);
  }

  canUpdatePwdUser(tokenId: number): Observable<boolean> {
    return this.http.get<boolean>(API_URL_AUTH + 'canUpdatePwdUser/' + tokenId);
  }
  
}
